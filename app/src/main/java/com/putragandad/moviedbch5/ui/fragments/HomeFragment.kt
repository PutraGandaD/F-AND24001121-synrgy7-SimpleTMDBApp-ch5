package com.putragandad.moviedbch5.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.putragandad.moviedbch5.R
import com.putragandad.moviedbch5.adapters.NowPlayingAdapter
import com.putragandad.moviedbch5.databinding.FragmentHomeBinding
import com.putragandad.moviedbch5.models.now_playing.NowPlayingResult
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel : MoviesViewModel by viewModels {
        MoviesViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.getMovieNowPlaying().observe(requireActivity()) { movies ->
            val result = movies.results
            setUpRecyclerView(result)

//            Log.d("OUTPUT", "$movies")
//            Log.d("RESULT", "$result")
        }
    }

    private fun setUpRecyclerView(dataset: List<NowPlayingResult>) {
        val adapter = NowPlayingAdapter(dataset, requireActivity())
        val recyclerView : RecyclerView? = view?.findViewById(R.id.now_playing_rv_container)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }
}