package wallet.raccoon.raccoonwallet.helper

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import wallet.raccoon.raccoonwallet.rest.ApiManager
import wallet.raccoon.raccoonwallet.rest.service.NodeExplorerApiService
import wallet.raccoon.raccoonwallet.type.NodeType
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils

object ActiveNodeHelper {
  var activeNodeList: List<NodeType>? = null
  var selectedNodeType: NodeType? = null

  fun auto(context: Context): Completable {
    return ApiManager.builderNodeExplorer()
        .create(NodeExplorerApiService::class.java)
        .getActiveNodeList()
        .doOnSuccess { nodeList ->
          if (nodeList.nodes.isEmpty()) {
            throw RuntimeException("active node list is empty")
          }

          activeNodeList = checkActiveNode(nodeList.nodes)
          saveNodeType(context, checkSelectedNode(context))
        }
        .subscribeOn(Schedulers.newThread())
        .toCompletable()
  }

  private fun checkActiveNode(activeNodeList: List<String>): List<NodeType> {
    return Observable.fromIterable(NodeType.values().toList())
        .filter {
          activeNodeList.contains(it.nodeBaseUrl)
        }
        .toList()
        .blockingGet() as List<NodeType>
  }

  //選択したnodeがactiveな場合それを返し、activeでない場合orNodeをまだ選択していない場合適当なnodeを返す
  private fun checkSelectedNode(context: Context): NodeType {
    val sharedPreferenceUtils = SharedPreferenceUtils(context)
    val selectedNodeName = sharedPreferenceUtils.nodeTypeName

    val nodeTypes = Observable.fromIterable(activeNodeList)
        .filter {
          it.name == selectedNodeName
        }
        .toList()
        .blockingGet() as List<NodeType>

    return if (nodeTypes.isEmpty()) {
      activeNodeList!![0]
    } else {
      nodeTypes[0]
    }
  }

  fun saveNodeType(
    context: Context,
    nodeType: NodeType?
  ) {
    nodeType?.let {
      val shareParcelUtils = SharedPreferenceUtils(context)
      shareParcelUtils.nodeTypeName = nodeType.nodeName
      this.selectedNodeType = nodeType
    }
  }
}

