package com.example.newsapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.presentation.uistate.NewsItemUIState
import com.example.newsapp.presentation.uistate.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Displays news details
 */
@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    private val viewModel: NewsListViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(layoutInflater)
        viewModel.selectedNewsItem.observe(viewLifecycleOwner){ uiState ->
            setNewsDetailsUI(uiState)
        }

        return binding.root
    }
    private fun setNewsDetailsUI(uiState: NewsItemUIState){
        binding.newsHeadline.text = uiState.title
        binding.newsDetails.text = uiState.description
        uiState.category.let {
            binding.newsCategoryValue.visibility = View.VISIBLE
            binding.newsCategoryValue.text = uiState.category }
        uiState.source.let {
            binding.newsSourceValue.visibility = View.VISIBLE
            binding.newsSourceValue.text = uiState.source }

        Glide.with(requireContext()).
        load(uiState.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.newsImageVIew)
    }

}