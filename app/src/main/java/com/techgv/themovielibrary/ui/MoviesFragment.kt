package com.techgv.themovielibrary.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.techgv.themovielibrary.data.CategorySet
import com.techgv.themovielibrary.databinding.FragmentMoviesBinding
import com.techgv.themovielibrary.ui.fragment.home.MovieViewModel
import com.techgv.themovielibrary.ui.fragment.home.ParentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private var list: MutableList<CategorySet> = mutableListOf()
    private lateinit var adapter: ParentAdapter
    private var movieApiResponse = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        initialData()
    }

    override fun onResume() {
        super.onResume()
        showView()
    }

    override fun onPause() {
        super.onPause()
        Log.i("MoviesFragment", "onPause")
    }

    private fun observers() {
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Popular","popular"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
            showView()
        }

        viewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Top Rated","top_rated"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
            showView()
        }

        viewModel.upcomingMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Up Coming","upcoming"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
            showView()
        }

        viewModel.nowPlayingMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Now Playing","now_playing"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
            showView()
        }
    }

    private fun initialData() {
        viewModel.popularMovies()
        viewModel.topRatedMovies()
        viewModel.upcomingMovies()
        viewModel.nowPlayingMovies()
        viewModel.latestMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.clear()
        initView()
        observers()
    }

    private fun initView() {

        adapter = ParentAdapter(list,requireContext())
        binding.homeRV.adapter = adapter
        binding.homeRV.setHasFixedSize(true)
        binding.homeRV.layoutManager = LinearLayoutManager(requireContext())

        adapter.onChildItemClickItem = { child, parent ->
            val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(list[parent].item[child].id.toLong())
            findNavController().navigate(action)
        }

        adapter.onCategoryClick ={
            val action = MoviesFragmentDirections.actionMoviesFragmentToCategoryDetailsFragment(list[it].title.first,list[it].title.second)
            findNavController().navigate(action)
        }

    }

    private fun showView() {
        movieApiResponse++
        if (movieApiResponse > 4) {
            binding.shimmer.visibility = View.GONE
            binding.homeRV.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.i("MoviesFragment", "onDestroy")
    }

}