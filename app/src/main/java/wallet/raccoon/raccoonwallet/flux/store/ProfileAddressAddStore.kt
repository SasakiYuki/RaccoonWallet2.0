package wallet.raccoon.raccoonwallet.flux.store

import io.reactivex.Observable
import wallet.raccoon.raccoonwallet.flux.getter.ProfileAddressAddActionCreator
import wallet.raccoon.raccoonwallet.flux.getter.ProfileAddressAddGetter
import wallet.raccoon.raccoonwallet.flux.reducer.ProfileAddressAddReducer
import wallet.raccoon.raccoonwallet.flux.type.ProfileAddressAddActionType
import wallet.raccoon.raccoonwallet.repository.MyProfileRepository
import javax.inject.Inject

class ProfileAddressAddStore @Inject constructor(private val repository: MyProfileRepository) :
    Store<ProfileAddressAddActionType, ProfileAddressAddActionCreator, ProfileAddressAddReducer, ProfileAddressAddGetter>() {
  override fun createActionCreator(
    dispatch: (ProfileAddressAddActionType) -> Unit,
    reducer: ProfileAddressAddReducer
  ): ProfileAddressAddActionCreator = ProfileAddressAddActionCreator(repository, dispatch, reducer)

  override fun createReducer(action: Observable<ProfileAddressAddActionType>): ProfileAddressAddReducer =
    ProfileAddressAddReducer(action)

  override fun createGetter(reducer: ProfileAddressAddReducer): ProfileAddressAddGetter =
    ProfileAddressAddGetter(reducer)
}
