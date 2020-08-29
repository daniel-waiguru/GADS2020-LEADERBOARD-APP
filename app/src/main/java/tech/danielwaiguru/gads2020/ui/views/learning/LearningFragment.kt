package tech.danielwaiguru.gads2020.ui.views.learning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.ui.viewmodels.LearningLeaderViewModel

@AndroidEntryPoint
class LearningFragment : Fragment() {
    private val learningLeaderViewModel: LearningLeaderViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning, container, false)
    }
}