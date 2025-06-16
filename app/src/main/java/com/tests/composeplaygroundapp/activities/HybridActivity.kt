package com.tests.composeplaygroundapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.tests.composeplaygroundapp.R
import com.tests.composeplaygroundapp.databinding.ActivityHybridBinding
import com.tests.composeplaygroundapp.fragments.FragmentWithCompose

class HybridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHybridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHybridBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.setFragmentBtn.setOnClickListener {
            binding.fragmentContainer.isVisible = true
            binding.setFragmentBtn.isVisible = false
            val fragment = FragmentWithCompose()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "TEST_FRAGMENT")
                .commitNowAllowingStateLoss()
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, HybridActivity::class.java)
    }
}