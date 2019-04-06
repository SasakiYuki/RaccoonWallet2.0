package wallet.raccoon.raccoonwallet.flux.type

sealed class TutorialLessonPrivateKeyDisplayActionType {
    class PrivateKey(val privateKey: String) : TutorialLessonPrivateKeyDisplayActionType()
}
