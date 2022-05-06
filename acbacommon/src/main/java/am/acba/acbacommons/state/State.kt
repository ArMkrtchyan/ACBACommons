package am.acba.acbacommons.state

sealed interface State {
    object Loading : State
    object Empty : State
    class Error(val throwable: Throwable) : State
    object Success : State
}