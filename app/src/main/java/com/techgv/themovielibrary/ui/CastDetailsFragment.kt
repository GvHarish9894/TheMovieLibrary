package com.techgv.themovielibrary.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.remote.response.CastDetailsResponse
import com.techgv.themovielibrary.data.remote.response.ExternalIdsResponse
import com.techgv.themovielibrary.data.remote.response.Movies
import com.techgv.themovielibrary.databinding.FragmentCastDetailsBinding
import com.techgv.themovielibrary.ui.fragment.home.ChildAdapter
import com.techgv.themovielibrary.ui.fragment.home.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastDetailsFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private var _binding: FragmentCastDetailsBinding? = null
    private val binding: FragmentCastDetailsBinding get() = _binding!!

    private var personId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args: CastDetailsFragmentArgs by navArgs()
            personId = args.personId.toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCastDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCastDetails(personId)
        viewModel.getExternalId(personId)
        viewModel.getMovieCredits(personId)
        initObservers()
    }

    private fun initObservers() {
        viewModel.castDetailsLivedata.observe(viewLifecycleOwner) {
            initView(it)
        }
        viewModel.externalIdsLivedata.observe(viewLifecycleOwner) {
            setExternalIds(it)
        }
        viewModel.moviesCreditsLivedata.observe(viewLifecycleOwner) {
            setMoviesCredits(it.cast)
        }

    }

    private fun setMoviesCredits(movieCredit: List<Movies>) {
        val adapter = ChildAdapter(movieCredit, requireContext())
        binding.movieCreditsRv.adapter = adapter
        binding.movieCreditsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        adapter.onChildClick = {
            findNavController().navigate(
                R.id.movieDetailsFragment,
                bundleOf("movie_id" to movieCredit[it].id.toLong())
            )
        }

    }

    private fun setExternalIds(externalId: ExternalIdsResponse) {
        externalId.let {
            it.facebook_id?.let { id ->
                binding.facebookIv.visibility = View.VISIBLE
                binding.facebookIv.setOnClickListener {
                    passIntent(getString(R.string.facebook, id))
                }
            }

            it.instagram_id?.let { id ->
                binding.instagramIv.visibility = View.VISIBLE
                binding.instagramIv.setOnClickListener {
                    passIntent(getString(R.string.instagram, id))
                }
            }

            it.twitter_id?.let { id ->
                binding.twitterIv.visibility = View.VISIBLE
                binding.twitterIv.setOnClickListener {
                    passIntent(getString(R.string.twitter, id))
                }
            }

        }
    }

    private fun initView(castDetailsResponse: CastDetailsResponse) {

        castDetailsResponse.let {
            Glide.with(requireContext())
                .load(getString(R.string.original, it.profile_path))
                .into(binding.castProfileIv)

            binding.castNameTv.text = it.name

            binding.knowingForTv.text = it.known_for_department
            binding.genderTv.text = it.gender.toString()
            binding.birthdateTv.text = it.birthday
            binding.placeOfBirthTv.text = it.place_of_birth


            binding.biographyTv.text = it.biography

        }

    }

    private fun passIntent(uri: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}