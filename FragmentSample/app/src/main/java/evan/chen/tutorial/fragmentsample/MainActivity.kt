package evan.chen.tutorial.fragmentsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import evan.chen.tutorial.fragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private val fragmentATag = "FragmentA"
    private val fragmentBTag = "FragmentB"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, AFragment.newInstance(), fragmentATag)
            transaction.addToBackStack(fragmentATag)
            transaction.commit()
        }

        binding.replaceToA.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, AFragment.newInstance())
            transaction.addToBackStack(fragmentATag)
            transaction.commit()
        }

        binding.replaceToB.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, BFragment.newInstance())
            transaction.addToBackStack(fragmentBTag)
            transaction.commit()
        }

        binding.addA.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag(fragmentATag)
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            if (fragment != null && fragment.isAdded) {
                Log.d(TAG, "${fragment::class.java} show")
                transaction.show(fragment)
                hideFragment(fragmentBTag)
            } else {
                Log.d(TAG, "AFragment add")
                transaction.add(R.id.container, AFragment.newInstance(), fragmentATag)

            }
            transaction.commit()
        }

        binding.addB.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag(fragmentBTag)
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            if (fragment != null && fragment.isAdded) {
                Log.d(TAG, "${fragment::class.java} show")
                transaction.show(fragment)
                hideFragment(fragmentATag)
            } else {
                Log.d(TAG, "BFragment add")
                transaction.add(R.id.container, BFragment.newInstance(), "FragmentB")
            }
            transaction.commit()
        }
    }

    private fun hideFragment(fragmentTag: String) {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        fragment?.let {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.hide(it)
            transaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}