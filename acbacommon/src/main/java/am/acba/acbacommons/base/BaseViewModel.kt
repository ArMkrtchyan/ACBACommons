package am.acba.acbacommons.base

import am.acba.acbacommons.state.State
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

abstract class BaseViewModel : ViewModel(), IRetry {

    val stateFlow = MutableStateFlow<State>(State.Success)
    protected suspend inline fun <reified T> handleNetworkResult(request: Flow<T>, showLoading: Boolean = true, showError: Boolean = true, crossinline onSuccess: (T) -> Unit) {
        request
            .onStart { if (showLoading) stateFlow.value = State.Loading }
            .catch { if (showError) stateFlow.value = State.Error(it) }
            .collectLatest {
                delay(3000)
                stateFlow.value = if (it is List<*> && it.isEmpty()) State.Empty else State.Success
                onSuccess(it)
            }
    }

    override fun retry() {
    }
}