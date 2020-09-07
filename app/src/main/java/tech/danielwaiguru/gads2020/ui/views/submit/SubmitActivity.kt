package tech.danielwaiguru.gads2020.ui.views.submit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_submit.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.common.gone
import tech.danielwaiguru.gads2020.common.toast
import tech.danielwaiguru.gads2020.common.utils.ProjectInputsValidator
import tech.danielwaiguru.gads2020.common.visible
import tech.danielwaiguru.gads2020.ui.viewmodels.SubmitViewModel
import tech.danielwaiguru.gads2020.ui.views.main.MainActivity
import tech.danielwaiguru.gads2020.ui.views.submit.dialog.ConfirmDialogFragment
import tech.danielwaiguru.gads2020.ui.views.submit.dialog.ErrorDialogFragment
import tech.danielwaiguru.gads2020.ui.views.submit.dialog.SuccessDialogFragment

@AndroidEntryPoint
class SubmitActivity : AppCompatActivity(), ConfirmDialogFragment.OnSubmitButtonClicked {
    private val inputValidator: ProjectInputsValidator by lazy { ProjectInputsValidator() }
    private val submitViewModel: SubmitViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        //setSupportActionBar(submitToolbar)

        subscribeToLiveData()
        initListeners()
    }
    private fun initListeners() {
        actionBack.setOnClickListener { initUi() }
        confirmButton.setOnClickListener { initConfirmDialog() }
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
        submitViewModel.loadingState.observe(this, {loading ->
            if (loading == true){
                showLoading()
            }
            else
            {
                hideLoading()
            }
        })
        submitViewModel.toast.observe(this, {
            this.toast(it)
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

    override fun onSubmitButtonClicked(button: View) {
        button.setOnClickListener { submitProject() }
    }

    //show Success dialog
    private fun initSuccessDialog(){
        val dialog = SuccessDialogFragment()
        dialog.show(supportFragmentManager, dialog.tag)
    }
    //show Failure dialog
    private fun initFailureDialog() {
        val dialog = ErrorDialogFragment()
        dialog.show(supportFragmentManager, dialog.tag)
    }
    private fun initUi() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun validateSubmission(): Boolean{
        var areCredentialsValid = true
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val projectLink = githubLinkEditText.text.toString()
        inputValidator.setCredentials(
            firstName, lastName, email, projectLink
        )
        if (!inputValidator.isFirstNameValid()){
            areCredentialsValid = false
            firstNameEditText.error = getString(R.string.first_name_error)
            firstNameEditText.requestFocus()
        }
        if (!inputValidator.isLastNameValid()){
            areCredentialsValid = false
            lastNameEditText.error = getString(R.string.last_name_error)
            lastNameEditText.requestFocus()
        }
        if (!inputValidator.isEmailValid()){
            areCredentialsValid = false
            emailEditText.error = getString(R.string.email_error)
            emailEditText.requestFocus()
        }
        if (!inputValidator.isProjectLinkValid()){
            areCredentialsValid = false
            githubLinkEditText.error = getString(R.string.link_error)
            githubLinkEditText.requestFocus()
        }
        return areCredentialsValid
    }
    private fun initConfirmDialog(){
        if (validateSubmission()){
            val dialog = ConfirmDialogFragment()
            dialog.setSubmitButtonClickListener(this)
            dialog.show(supportFragmentManager, dialog.tag)
        }
    }
    private fun showLoading(){
        submitProgressBar.visible()
    }
    private fun hideLoading(){
        submitProgressBar.gone()
    }
}