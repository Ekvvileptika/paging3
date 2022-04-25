package com.example.myapplication.paging3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPagingBinding
import kotlinx.coroutines.flow.collectLatest

class Paging3Activity: AppCompatActivity() {
    val viewModel: Paging3ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_paging)
        val activity: ActivityPagingBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging)

        val characterAdapter = Paging3Recycler()
        activity.characters.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }

        lifecycleScope.launchWhenCreated {
            viewModel.characters.collectLatest { pagingData ->
                characterAdapter.submitData(pagingData)
            }
        }
    }
}