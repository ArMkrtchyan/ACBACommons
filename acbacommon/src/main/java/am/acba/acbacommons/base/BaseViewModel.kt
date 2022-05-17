package am.acba.acbacommons.base

import am.acba.acbacommons.state.State
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel : ViewModel(), IRetry {

    val stateFlow = MutableStateFlow<State>(State.Success)

    protected suspend inline fun <reified T> handleNetworkResult(
        request: Flow<T>,
        showLoading: Boolean = true,
        showError: Boolean = true,
        crossinline onSuccess: (T) -> Unit
    ) {
        request
            .onStart { if (showLoading) stateFlow.value = State.Loading }
            .catch { if (showError) stateFlow.value = State.Error(it) }
            .onCompletion { stateFlow.value = if (it is List<*> && it.isEmpty()) State.Empty else State.Success }
            .collectLatest {
                onSuccess(it)
                Log.i("MakeRequestTag", "Success: $it")
            }
    }

    protected suspend inline fun <reified T, K> handlePairNetworkResult(
        first: Flow<T>,
        second: Flow<K>,
        showLoading: Boolean = true,
        showError: Boolean = true,
        crossinline onSuccess: (Pair<T, K>) -> Unit
    ) {
        first.zip(second) { firstResponse, secondResponse ->
            onSuccess(Pair(firstResponse, secondResponse))
            Log.i("MakeRequestTag", "Success: ${Pair(firstResponse, secondResponse)}")
        }
            .onStart { if (showLoading) stateFlow.value = State.Loading }
            .catch { if (showError) stateFlow.value = State.Error(it) }
            .onCompletion { stateFlow.value = State.Success }
            .collect()
    }

    protected suspend inline fun <reified T, K, F> handleTripleNetworkResult(
        first: Flow<T>,
        second: Flow<K>,
        third: Flow<F>,
        showLoading: Boolean = true,
        showError: Boolean = true,
        crossinline onSuccess: (Triple<T, K, F>) -> Unit
    ) {
        var firstRes: T? = null
        var secondRes: K? = null
        var thirdRes: F? = null
        first.zip(second) { firstResponse, secondResponse ->
            firstRes = firstResponse
            secondRes = secondResponse
        }.zip(third) { _, thirdResponse ->
            thirdRes = thirdResponse
        }
            .onStart { if (showLoading) stateFlow.value = State.Loading }
            .catch { if (showError) stateFlow.value = State.Error(it) }
            .onCompletion { stateFlow.value = State.Success }
            .collectLatest {
                if (firstRes != null && secondRes != null && thirdRes != null) {
                    onSuccess(Triple(firstRes!!, secondRes!!, thirdRes!!))
                    Log.i("MakeRequestTag", "Success: ${Triple(firstRes!!, secondRes!!, thirdRes!!)}")
                }
            }
    }

    override fun retry() {
    }
}