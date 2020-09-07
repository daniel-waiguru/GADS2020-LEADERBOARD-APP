package tech.danielwaiguru.gads2020.ui.views.submit.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_confirm.*
import tech.danielwaiguru.gads2020.R

class ConfirmDialogFragment : DialogFragment() {
    private var clickListener: OnSubmitButtonClicked? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setCancelable(false)
    }
    private fun initListeners(){
        cancelImageView.setOnClickListener { closeConfirmDialog() }
        onSubmitButtonClicked(submitButton)
    }
    private fun closeConfirmDialog(){
        dialog?.dismiss()
    }
    private fun onSubmitButtonClicked(button: View){
        clickListener?.onSubmitButtonClicked(button)
    }
    fun setSubmitButtonClickListener(listener: OnSubmitButtonClicked){
        this.clickListener = listener
    }
    interface OnSubmitButtonClicked {
        fun onSubmitButtonClicked(button: View)
    }
}