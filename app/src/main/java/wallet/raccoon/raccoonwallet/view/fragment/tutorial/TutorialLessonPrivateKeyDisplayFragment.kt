package wallet.raccoon.raccoonwallet.view.fragment.tutorial

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tutorial_lesson_private_key_display.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wallet.raccoon.raccoonwallet.R
import wallet.raccoon.raccoonwallet.di.ViewModelFactory
import wallet.raccoon.raccoonwallet.type.TutorialType
import wallet.raccoon.raccoonwallet.type.TutorialType.NEWBIE
import wallet.raccoon.raccoonwallet.type.TutorialType.RACCOON
import wallet.raccoon.raccoonwallet.util.PinCodeProvider
import wallet.raccoon.raccoonwallet.util.WalletProvider
import wallet.raccoon.raccoonwallet.view.fragment.BaseFragment
import wallet.raccoon.raccoonwallet.viewmodel.TutorialLessonPrivateKeyDisplayViewModel
import javax.inject.Inject


class TutorialLessonPrivateKeyDisplayFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TutorialLessonPrivateKeyDisplayViewModel

    override fun layoutRes() = R.layout.fragment_tutorial_lesson_private_key_display

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TutorialLessonPrivateKeyDisplayViewModel::class.java)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        completeButton.setOnClickListener {
            when (arguments?.getSerializable(
                ARG_TUTORIAL_TYPE
            ) as? TutorialType ?: NEWBIE) {
                NEWBIE ->
                    replaceFragment(TutorialLessonForNewbiePinCodeFragment.newInstance(), true)
                RACCOON ->
                    replaceFragment(TutorialLessonFinishFragment.newInstance(RACCOON), true)
            }
        }

        loadPrivateKey()
    }

    private fun setupViewModel() {
        viewModel.decryptPrivateKey.observe(this, Observer { key ->
            hideProgress()
            privateKeyTextView.text = key
        })
    }

    private fun loadPrivateKey() {
        WalletProvider.wallet?.let {
            showProgress()

            val pin = PinCodeProvider.getPinCode()

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.decryptPrivateKey(it.id, pin)
            }
        }
    }

    companion object {
        private const val ARG_TUTORIAL_TYPE = "tutorial_type"
        fun newInstance(tutorialType: TutorialType): TutorialLessonPrivateKeyDisplayFragment {
            val fragment = TutorialLessonPrivateKeyDisplayFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_CONTENTS_NAME_ID, R.string.tutorial_description_fragment_title)
                putSerializable(ARG_TUTORIAL_TYPE, tutorialType)
            }
            return fragment
        }
    }
}