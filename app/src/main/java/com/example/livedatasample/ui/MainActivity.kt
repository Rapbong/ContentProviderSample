package com.example.livedatasample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.livedatasample.R
import com.example.livedatasample.databinding.ActivityMainBinding
import com.example.livedatasample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.eventData.observe(this, Observer<String> { text -> changeText1(text) })
        viewModel.transformationData.observe(this, Observer<String> { text -> changeText2(text) })
        viewModel.transformationData2.observe(this, Observer<String> { text -> changeText3(text) })
    }

    private fun changeText1(text: String) {
        text_view_1.text = text
    }

    private fun changeText2(text: String) {
        text_view_2.text = text
    }

    private fun changeText3(text: String) {
        text_view_3.text = text
    }
}
