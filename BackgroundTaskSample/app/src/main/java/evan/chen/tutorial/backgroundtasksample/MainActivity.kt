package evan.chen.tutorial.backgroundtasksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.content.UnusedAppRestrictionsConstants.*
import evan.chen.tutorial.backgroundtasksample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            handleRestrictions(1)
//            val future = PackageManagerCompat.getUnusedAppRestrictionsStatus(this)
//            future.addListener(
//                { onResult(future.get()) },
//                ContextCompat.getMainExecutor(this)
//            )
        }
    }

//    fun onResult(appRestrictionsStatus: Int) {
//        when (appRestrictionsStatus) {
//            // Couldn't fetch status. Check logs for details.
//            ERROR -> {}
//
//            // Restrictions don't apply to your app on this device.
//            FEATURE_NOT_AVAILABLE -> {}
//
//            // The user has disabled restrictions for your app.
//            DISABLED -> {}
//
//            // If the user doesn't start your app for a few months, the system will
//            // place restrictions on it. See the API_* constants for details.
//            API_30_BACKPORT, API_30, API_31 -> handleRestrictions(appRestrictionsStatus)
//        }
//    }

    fun handleRestrictions(appRestrictionsStatus: Int) {
        // If your app works primarily in the background, you can ask the user
        // to disable these restrictions. Check if you have already asked the
        // user to disable these restrictions. If not, you can show a message to
        // the user explaining why permission auto-reset or app hibernation should be
        // disabled. Then, redirect the user to the page in system settings where they
        // can disable the feature.
        val intent = IntentCompat.createManageUnusedAppRestrictionsIntent(this, packageName)

        // You must use startActivityForResult(), not startActivity(), even if
        // you don't use the result code returned in onActivityResult().
//        startActivityForResult(intent, REQUEST_CODE)
        startActivity(intent)
    }

}
