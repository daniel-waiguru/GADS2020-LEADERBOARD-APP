package tech.danielwaiguru.gads2020.ui.views.iq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_iq.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.IQLeaderAdapter
import tech.danielwaiguru.gads2020.adapters.LearningLeaderAdapter
import tech.danielwaiguru.gads2020.common.toast
import tech.danielwaiguru.gads2020.ui.viewmodels.IQLeaderViewModel
import tech.danielwaiguru.gads2020.ui.viewmodels.LearningLeaderViewModel

@AndroidEntryPoint
class IQFragment : Fragment() {
    private val iqLeaderViewModel: IQLeaderViewModel by viewModels()
    private val iqLeaderAdapter: IQLeaderAdapter by lazy { IQLeaderAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        iqLeaderViewModel.fetchIQLeaders()
        iqLeaderViewModel.toast.observe(viewLifecycleOwner, {
            requireActivity().toast(it)
        })
        iqLeaderViewModel.learningLeaders.observe(viewLifecycleOwner, {
            iqLeaderAdapter.setData(it)
        })
    }
    private fun setupRecyclerView() = skillIQRecyclerView.apply {
        this.adapter = iqLeaderAdapter
        this.layoutManager = LinearLayoutManager(requireContext())
    }
}