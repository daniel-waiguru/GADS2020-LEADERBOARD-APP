package tech.danielwaiguru.gads2020.ui.views.learning

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_learning.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.adapters.LearningLeaderAdapter
import tech.danielwaiguru.gads2020.common.gone
import tech.danielwaiguru.gads2020.common.visible
import tech.danielwaiguru.gads2020.networking.NetworkStatusChecker
import tech.danielwaiguru.gads2020.ui.viewmodels.LearningLeaderViewModel
import timber.log.Timber

@SuppressLint("NewApi")
@AndroidEntryPoint
class LearningFragment : Fragment() {
    private val learningLeaderViewModel: LearningLeaderViewModel by viewModels()
    private val networkStatusChecker: NetworkStatusChecker by lazy {
        NetworkStatusChecker(requireActivity().getSystemService(ConnectivityManager::class.java))
    }
    private val learningLeaderAdapter: LearningLeaderAdapter by lazy { LearningLeaderAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        networkStatusChecker.performIfConnectedToInternet(::displayNoNetworkMessage){
            learningLeaderViewModel.fetchLearningLeaders()
        }
        learningLeaderViewModel.toast.observe(viewLifecycleOwner, {message ->
            if (message != null){
                errorTitle.text = message
                showErrorView()
                hideLoading()
            }
        })
        learningLeaderViewModel.loadingState.observe(viewLifecycleOwner, {
            if (it == true){
                showLoadingDialog()
            }
            else {
                hideLoading()
            }
        })
        learningLeaderViewModel.learningLeaders.observe(viewLifecycleOwner, {
            Timber.d(it.size.toString())
            learningLeaderAdapter.initData(it)
        })
    }
    private fun setupRecyclerView() = learningRecyclerView.apply {
        this.adapter = learningLeaderAdapter
        this.layoutManager = LinearLayoutManager(requireContext())
    }
    /**
     * no network alert
     */
    private fun displayNoNetworkMessage(){
        view?.let {
            errorTitle.text = getText(R.string.no_connection)
            errorSubtitle.text = getText(R.string.no_connection_error)
            showErrorView()
        }
    }
    private fun showErrorView(){
        errorView.visible()
        learningRecyclerView.gone()
    }
    private fun showLoadingDialog(){
        leanerProgressBar.visible()
        learningRecyclerView.gone()
        errorView.gone()
    }
    private fun hideLoading(){
        leanerProgressBar.gone()
        learningRecyclerView.visible()
    }
}