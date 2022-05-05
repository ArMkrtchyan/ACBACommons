package am.acba.acbacommons.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel() {

    val stateFlow = MutableStateFlow<Int>(0)
    protected suspend inline fun <reified T> handleNetworkResult(request: Flow<T>, showLoading: Boolean = true, showError: Boolean = true, crossinline onSuccess: (T) -> Unit) {
        request.onStart {
            if (showLoading) stateFlow.value = 1
            Log.i("MakeRequestTag", "OnStart")
        }
            .catch {
                delay(3000);
                if (showError) stateFlow.value = 2
                Log.i("MakeRequestTag", "Error: ${it.localizedMessage}")
            }
            .collectLatest {
                delay(3000);
                stateFlow.value = 3;
                onSuccess(it)
            }
    }
}