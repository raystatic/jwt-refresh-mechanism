package com.example.jwtrefreshdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.jwtrefreshdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val factory = MainViewModel.Factory(Repository(sharedPreferences))

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.newData.observe(this) {
            it?.let { res->
                if (res.isLoading) {
                    binding.text.text = "Loading data from server"
                } else {
                    val text = "${res.data} with message ${res.message}"
                    binding.text.text = text
                }
            }
        }

        binding.btn.setOnClickListener {
            viewModel.getSomeData()
        }

    }
}