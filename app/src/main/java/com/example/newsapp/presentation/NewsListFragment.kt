package com.example.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.presentation.adapter.NewsListAdapter
import com.example.newsapp.presentation.uistate.NewsItemUIState
import com.example.newsapp.presentation.uistate.NewsListViewModel
import com.example.newsapp.presentation.uistate.NewsListUIState
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * This class displays list of news
 */
@AndroidEntryPoint
class NewsListFragment : Fragment() {
    private lateinit var binding: FragmentNewsListBinding
    private val adapter = NewsListAdapter(::onItemClicked)

   private val viewModel: NewsListViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newslist.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.newslist.adapter =adapter
        //observe livedata from viewModel
        lifecycleScope.launchWhenCreated{
            viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
                updateUI(viewState)
            }
            viewModel.fetchNews()
        }
    }

    // Method to handle UI state updates
    private fun updateUI(viewState: NewsListUIState) {
        when (viewState) {
            is NewsListUIState.Content -> {
                binding.newslist.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                adapter.setData(viewState.newsList)
            }
            is NewsListUIState.Error-> {
                binding.newslist.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
                Toast.makeText(activity,viewState.message, Toast.LENGTH_LONG).show()
            }
           is NewsListUIState.Loading -> {
                binding.newslist.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }

    //method to handle nes it clicked event
    private fun onItemClicked(newsItemUIState: NewsItemUIState) {
        viewModel.selectNews(newsItemUIState)
        findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment)
    }


}