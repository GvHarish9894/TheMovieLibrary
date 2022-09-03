package com.techgv.themovielibrary.ui.categorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.techgv.themovielibrary.data.remote.response.Movies
import com.techgv.themovielibrary.databinding.FragmentCategoryDetailsBinding
import com.techgv.themovielibrary.ui.CategoryDetailsAdapter
import com.techgv.themovielibrary.ui.activity.MainActivity
import com.techgv.themovielibrary.ui.fragment.home.MovieViewModel


class CategoryDetailsFragment : Fragment() {

    private var _binding: FragmentCategoryDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by activityViewModels()

    private lateinit var adapter: CategoryDetailsAdapter
    private var list: MutableList<Movies> = mutableListOf()
    private var category = ""
    private var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args: CategoryDetailsFragmentArgs by navArgs()
            title = args.category
            category = args.categoryKey
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieCategoryDetails(category)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.movieCategoryDetailLivedata.observe(viewLifecycleOwner) {
            list.addAll(it.results)
            binding.recyclerView.adapter?.notifyItemInserted(list.size)
        }
    }

    private fun initViews() {
        (activity as MainActivity).setActionBarTitle(title)
        adapter = CategoryDetailsAdapter(list, requireContext())
        binding.recyclerView.adapter = CategoryDetailsAdapter(list, requireContext())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}