package tech.danielwaiguru.gads2020.ui.views.submit.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.fragment_confirm.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.ui.viewmodels.SubmitViewModel

@AndroidEntryPoint
class ConfirmDialogFragment : DialogFragment() {
    private val submitViewModel: SubmitViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initListeners()
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)
    }
    private fun initListeners(){
        cancelImageView.setOnClickListener { closeConfirmDialog() }
        submitButton.setOnClickListener {  }
    }
    private fun closeConfirmDialog(){
        dialog?.dismiss()
    }
    private fun subscribeToLiveData(){
        submitViewModel.submissionSuccess.observe(this, {
            if (it == true){
                initSuccessDialog()
            }
            else{
                initFailureDialog()
            }
        })
    }
    //submit project details
    private fun submitProject(){
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val projectLink = githubLinkEditText.text.toString()
        submitViewModel.submitProject(
            firstName, lastName, email,projectLink
        )
    }
    //show Success dialog
    private fun initSuccessDialog(){
        val dialog = SuccessDialogFragment()
        dialog.show(childFragmentManager, dialog.tag)
    }
    //show Failure dialog
    private fun initFailureDialog() {
        val dialog = ErrorDialogFragment()
        dialog.show(childFragmentManager, dialog.tag)
    }
}