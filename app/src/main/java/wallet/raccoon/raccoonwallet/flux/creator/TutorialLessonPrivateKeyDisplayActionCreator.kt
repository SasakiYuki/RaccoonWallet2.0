package wallet.raccoon.raccoonwallet.flux.creator

import wallet.raccoon.raccoonwallet.db.Database
import wallet.raccoon.raccoonwallet.flux.store.DisposableMapper
import wallet.raccoon.raccoonwallet.flux.type.TutorialLessonPrivateKeyDisplayActionType
import wallet.raccoon.raccoonwallet.usecase.TutorialLessonPrivateKeyDisplayUseCase


class TutorialLessonPrivateKeyDisplayActionCreator(
    private val useCase: TutorialLessonPrivateKeyDisplayUseCase,
    private val dispatch: (TutorialLessonPrivateKeyDisplayActionType) -> Unit
) : DisposableMapper() {

    suspend fun decryptPrivateKey(walletId: Long, pin: ByteArray) {
        Database.query(useCase.decryptPrivateKey(walletId, pin), { key ->
            dispatch(TutorialLessonPrivateKeyDisplayActionType.PrivateKey(key))
        })
    }
}
