package tech.danielwaiguru.gads2020.ui.views.iq

import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_iq.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.IQLeaderAdapter
import tech.danielwaiguru.gads2020.common.gone
import tech.danielwaiguru.gads2020.common.visible
import tech.danielwaiguru.gads2020.networking.NetworkStatusChecker
import tech.danielwaiguru.gads2020.ui.viewmodels.IQLeaderViewModel

@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class IQFragment : Fragment() {
    private val iqLeaderViewModel: IQLeaderViewModel by viewModels()
    private val iqLeaderAdapter: IQLeaderAdapter by lazy { IQLeaderAdapter() }
    private val networkStatusChecker: NetworkStatusChecker by lazy {
        NetworkStatusChecker(requireActivity().getSystemService(ConnectivityManager::class.java))
    }
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
        networkStatusChecker.performIfConnectedToInternet(::displayNoNetworkMessage){
            iqLeaderViewModel.fetchIQLeaders()
        }
        iqLeaderViewModel.toast.observe(viewLifecycleOwner, {message ->
            if (message != null){
                errorTitle.text = message
                showErrorView()
            }
        })
        iqLeaderViewModel.learningLeaders.observe(viewLifecycleOwner, {
            iqLeaderAdapter.setData(it)
        })
    }
    private fun setupRecyclerView() = skillIQRecyclerView.apply {
        this.adapter = iqLeaderAdapter
        this.layoutManager = when (resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT -> {
                LinearLayoutManager(requireContext())
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            }
            else -> throw IllegalStateException(context.getString(R.string.state_error))
        }
    }
    private fun displayNoNetworkMessage(){
        view?.let {
            errorTitle.text = getText(R.string.no_connection)
            errorSubtitle.text = getText(R.string.no_connection_error)
            showErrorView()
        }
    }
    private fun showErrorView(){
        skillIQRecyclerView.gone()
        errorView.visible()
    }
}