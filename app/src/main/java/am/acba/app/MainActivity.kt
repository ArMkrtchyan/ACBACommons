package am.acba.app

import am.acba.acbacommons.validators.Validator
import am.acba.app.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            validate.setOnClickListener {
                validate(root)
            }
        }
    }

    private fun validate(root: View) {
        if (root is ViewGroup) {
            for (view in root.children) {
                validate(view)
            }
        } else if (root is Validator) {
            if (root.isRequiredForValidation()) {
                root.isValid()
            }
        }
    }
}