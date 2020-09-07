package tech.danielwaiguru.gads2020.common.utils

import android.util.Patterns

class ProjectInputsValidator : Validator {
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var projectLink: String
    override fun setCredentials(
        firstName: String,
        lastName: String,
        email: String,
        projectLink: String
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.projectLink = projectLink
    }

    override fun isFirstNameValid(): Boolean = firstName.isNotEmpty()

    override fun isLastNameValid(): Boolean = lastName.isNotEmpty()

    override fun isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun isProjectLinkValid(): Boolean = projectLink.isEmpty() &&
            Patterns.WEB_URL.matcher(projectLink).matches()
}