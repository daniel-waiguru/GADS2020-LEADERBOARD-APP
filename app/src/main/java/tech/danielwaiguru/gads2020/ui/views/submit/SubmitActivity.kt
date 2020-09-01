package tech.danielwaiguru.gads2020.ui.views.submit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_submit.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.common.utils.ProjectInputsValidator
import tech.danielwaiguru.gads2020.ui.views.main.MainActivity

class SubmitActivity : AppCompatActivity() {
    private val inputValidator: ProjectInputsValidator by lazy { ProjectInputsValidator() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        //setSupportActionBar(submitToolbar)
        initListeners()
    }
    private fun initListeners() {
        actionBack.setOnClickListener { initUi() }
        submitButton.setOnClickListener {  }
    }
    private fun initUi() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    private fun submitProject(){
        validateSubmission()
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val projectLink = githubLinkEditText.text.toString()
    }
    private fun validateSubmission(){
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val projectLink = githubLinkEditText.text.toString()
        inputValidator.setCredentials(
            firstName, lastName, email, projectLink
        )
        if (!inputValidator.isFirstNameValid()){
            firstNameEditText.error = "First name is required!"
            firstNameEditText.requestFocus()
        }
        if (!inputValidator.isLastNameValid()){
            lastNameEditText.error = "Last name is required!"
            lastNameEditText.requestFocus()
        }
        if (!inputValidator.isEmailValid()){
            emailEditText.error = "Enter a valid email address!"
            emailEditText.requestFocus()
        }
        if (!inputValidator.isProjectLinkValid()){
            githubLinkEditText.error = "Project link is required!"
            githubLinkEditText.requestFocus()
        }
    }
}