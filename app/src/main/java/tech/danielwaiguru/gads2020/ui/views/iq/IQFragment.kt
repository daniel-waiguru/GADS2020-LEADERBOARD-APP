package tech.danielwaiguru.gads2020.ui.views.iq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_iq.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.LearningLeaderAdapter
import tech.danielwaiguru.gads2020.ui.viewmodels.IQLeaderViewModel
import tech.danielwaiguru.gads2020.ui.viewmodels.LearningLeaderViewModel


class IQFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iq, container, false)
    }
}