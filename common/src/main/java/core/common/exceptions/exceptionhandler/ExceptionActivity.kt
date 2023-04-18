package core.common.exceptions.exceptionhandler

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import core.common.databinding.ActivityExceptionBinding
import core.common.shared.extensions.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExceptionActivity : AppCompatActivity() {

    private val _binding by lazy { ActivityExceptionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        _binding.restartApplication.setOnClickListener {
            val appPackage = intent.getStringExtra("packageName")
            if (appPackage != null) {
                val i = applicationContext.packageManager.getLaunchIntentForPackage(appPackage)
                i?.data = Uri.parse(appPackage)
                startActivity(i)
                finish()
            }
        }

        val model = intent.getParcelableExtra<ErrorModel>("model")
        Log.e("ErrorTag", model.toString())
        val apiA = intent.getStringExtra("apiA") ?: ""
        val apiB = intent.getStringExtra("apiB") ?: ""
        val apiC = intent.getStringExtra("apiC") ?: ""
        val iService = RestService().getRetrofitInstance()

        val call = iService.loadChanges(apiA, apiB, apiC, model)
        call?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                response.log()
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                t.localizedMessage.log()
            }
        })
    }
}