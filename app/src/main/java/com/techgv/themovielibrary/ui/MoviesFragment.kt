package com.techgv.themovielibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.techgv.themovielibrary.data.CategorySet
import com.techgv.themovielibrary.databinding.FragmentMoviesBinding
import com.techgv.themovielibrary.ui.home.MovieViewModel
import com.techgv.themovielibrary.ui.home.ParentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {


    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private  var list: MutableList<CategorySet> = mutableListOf()
    private lateinit var adapter: ParentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        initialData()
    }

    private fun observers() {
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Popular","popular"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
        }

        viewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Top Rated","top_rated"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
        }

        viewModel.upcomingMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Up Coming","upcoming"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
        }

        viewModel.nowPlayingMoviesLiveData.observe(viewLifecycleOwner) {
            list.add(CategorySet(Pair("Now Playing","now_playing"), it.results))
            binding.homeRV.adapter?.notifyItemInserted(list.size)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}