package tech.danielwaiguru.gads2020.ui.views.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_learning.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.LearningLeaderAdapter
import tech.danielwaiguru.gads2020.common.toast
import tech.danielwaiguru.gads2020.ui.viewmodels.LearningLeaderViewModel
import timber.log.Timber

@AndroidEntryPoint
class LearningFragment : Fragment() {
    private val viewModel: LearningLeaderViewModel by viewModels()
    private val adapter: LearningLeaderAdapter by lazy { LearningLeaderAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchLearningLeaders()
        viewModel.toast.observe(viewLifecycleOwner, {
            requireActivity().toast(it)
        })
        viewModel.learningLeaders.observe(viewLifecycleOwner, {
            Timber.d(it.size.toString())
            adapter.submitList(it)
        })
    }
    private fun setupRecyclerView() = learningRecyclerView.apply {
        this.layoutManager = LinearLayoutManager(requireContext())
        this.adapter = adapter
    }
}