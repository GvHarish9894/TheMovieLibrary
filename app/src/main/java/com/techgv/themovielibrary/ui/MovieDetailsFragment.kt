package com.techgv.themovielibrary.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.play.core.review.ReviewManagerFactory
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.remote.response.*
import com.techgv.themovielibrary.databinding.FragmentMovieDetailsBinding
import com.techgv.themovielibrary.ui.fragment.home.ChildAdapter
import com.techgv.themovielibrary.ui.fragment.home.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieDetailsResponse: MovieDetailsResponse
    private val viewModel: MovieViewModel by viewModels()
    private var movieId = 0

    private var similarMovies: MutableList<Movies> = mutableListOf()
    private var recommendationMovies: MutableList<Movies> = mutableListOf()
    private var videosList: MutableList<VideoResults> = mutableListOf()
    private var castList: MutableList<Cast> = mutableListOf()
    private var imagesList: MutableList<Poster> = mutableListOf()

    private var numOfApiResponse = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args: MovieDetailsFragmentArgs by navArgs()
            args.movieId.let {
                movieId = it.toInt()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMoviesDetails(movieId)
        viewModel.getSimilarMovies(movieId)
        viewModel.getCredits(movieId)
        viewModel.getVideos(movieId)
        viewModel.getImages(movieId)
        viewModel.getRecommendMovies(movieId)
        initObservers()
    }

    private fun initObservers() {
        viewModel.moviesDetailsLivedata.observe(viewLifecycleOwner) {
            it.let {
                movieDetailsResponse = it
                initViews()
                showViews()
            }
        }

        viewModel.getSimilarMoviesLivedata.observe(viewLifecycleOwner) {
            it.let {
                similarMovies.addAll(it.results)
                updateSimilarMovies()
                showViews()
            }
        }
        viewModel.getRecommendationMoviesLivedata.observe(viewLifecycleOwner) {
            it.let {
                recommendationMovies.addAll(it.results)
                updateRecommendation()
                showViews()
            }
        }
        viewModel.videosLivedata.observe(viewLifecycleOwner) {
            it.let {
                videosList.addAll(it.results)
                updateVideosRv()
                showViews()
            }
        }

        viewModel.creditsLivedata.observe(viewLifecycleOwner) {
            it.let {
                castList.addAll(it.cast)
                updateCastDetails()
                showViews()
            }
        }

        viewModel.imagesLivedata.observe(viewLifecycleOwner) {
            it.let {
                imagesList.addAll(it.posters)
                updateImageRV()
                showViews()
            }
        }
    }

    private fun initViews() {
        movieDetailsResponse.let {
            binding.nameTv.text = it.title
            binding.tagLineTv.text = it.tagline
            binding.releaseDateTv.text = it.release_date
            binding.biographyTv.text = it.overview
            binding.ratingTv.text = String.format("%.1f", it.vote_average)
            Glide.with(this).load(this.getString(R.string.image_w780, it.poster_path))
                .into(binding.moviePosterIv)
            Glide.with(this).load(this.getString(R.string.original, it.backdrop_path))
                .into(binding.movieBackdropIv)
            updateGenres()
        }

        inAppReview()

    }

    private fun updateGenres() {
        val adapter = GenresAdapter(movieDetailsResponse.genres)
        binding.genresList.adapter = adapter
        binding.genresList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.onItemClicked = {
            Log.i("Genres", it.toString())
        }
    }

    private fun updateSimilarMovies() {

        val adapter = ChildAdapter(similarMovies, requireContext())
        binding.similarMoviesRv.adapter = adapter
        binding.similarMoviesRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.onChildClick = {

            /**Problem: Avoid Same fragment Added multiple in backstack
             * For Remove Current Fragment before navigating same fragment*/
            findNavController().popBackStack(R.id.movieDetailsFragment, true)
            findNavController().navigate(
                R.id.movieDetailsFragment,
                bundleOf("movie_id" to similarMovies[it].id.toLong())
            )
        }

    }

    override fun onResume() {
        super.onResume()
        showViews()
    }

    private fun updateRecommendation() {
        val adapter = ChildAdapter(recommendationMovies, requireContext())
        binding.recommendationsRv.adapter = adapter
        binding.recommendationsRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.onChildClick = {

            /**Problem: Avoid Same fragment Added multiple in backstack
             * For Remove Current Fragment before navigating same fragment*/
            findNavController().popBackStack(R.id.movieDetailsFragment, true)
            findNavController().navigate(
                R.id.movieDetailsFragment,
                bundleOf("movie_id" to recommendationMovies[it].id.toLong())
            )
        }

    }

    private fun updateVideosRv() {
        val adapter = VideoAdapter(videosList)
        binding.videoClipsRecyclerView.adapter = adapter
        binding.videoClipsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun updateCastDetails() {
        val adapter = CastAdapter(castList, requireContext())
        binding.castRecyclerView.adapter = adapter
        binding.castRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.onItemClickListener = {
            val action =
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToCastDetailsFragment(
                    castList[it].id.toLong()
                )
            findNavController().navigate(action)
        }

    }

    private fun updateImageRV() {
        val adapter = ImageAdapter(imagesList, requireContext())
        binding.postersRv.adapter = adapter
        binding.postersRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun hideShimmerEffect() {
        binding.topContainerShimmer.visibility = View.GONE
        binding.genresListShimmer.visibility = View.GONE
        binding.castListShimmer.visibility = View.GONE
        binding.videoClipsShimmer.visibility = View.GONE
        binding.moviePostersShimmer.visibility = View.GONE
        binding.recommendationsShimmer.visibility = View.GONE
        binding.similarMoviesShimmer.visibility = View.GONE
    }

    private fun showViews() {
        numOfApiResponse++
        if (numOfApiResponse > 6) {
            hideShimmerEffect()
            binding.topContainer.visibility = View.VISIBLE
            binding.genresList.visibility = View.VISIBLE
            binding.castListLL.visibility = View.VISIBLE
            binding.videoClipsLL.visibility = View.VISIBLE
            binding.moviePostersLL.visibility = View.VISIBLE
            binding.recommendationsLL.visibility = View.VISIBLE
            binding.similarMoviesLL.visibility = View.VISIBLE
        }
    }

    private fun inAppReview() {
        val reviewManager = ReviewManagerFactory.create(requireContext())
        val requestReviewFlow = reviewManager.requestReviewFlow()
        requestReviewFlow.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = request.result
                val flow = reviewManager.launchReviewFlow(requireActivity(), reviewInfo)
                flow.addOnCompleteListener {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                }
            } else {
                Log.d("Error: ", request.exception.toString())
                // There was some problem, continue regardless of the result.
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}