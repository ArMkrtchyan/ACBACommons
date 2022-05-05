package am.acba.acbacommons.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest

abstract class BaseFragmentWithViewModel : AppCompatActivity() {
    protected abstract val mViewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        lifecycleScope.launchWhenResumed {
            mViewModel.stateFlow.collectLatest {

            }
        }
    }
}