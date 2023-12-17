package com.slothdeboss.espressopracticetemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.slothdeboss.espressopracticetemplate.databinding.ActivityMainBinding
import com.slothdeboss.espressopracticetemplate.ui.entry.AppEntryFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.commit {
            add(R.id.flContainer, AppEntryFragment.newInstance())
        }
    }
}
