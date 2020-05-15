package com.techm.employee_management.view

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techm.employee_management.R
import com.techm.employee_management.model.ModelEmployeeRegistration
import com.techm.employee_management.utils.ResponseStatus
import com.techm.employee_management.utils.toast
import com.techm.employee_management.viewmodel.ViewModelAddEmployee
import kotlinx.android.synthetic.main.fragment_add_employee.*
import kotlinx.android.synthetic.main.fragment_add_employee.view.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddEmployee : Fragment() {
    private lateinit var mDataViewModel: ViewModelAddEmployee
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_employee, container, false)
        activity?.title = getString(R.string.add_employee)

        mDataViewModel = ViewModelProvider(this).get(ViewModelAddEmployee::class.java)
        setupProgressDialog()

        view.buttonSave.setOnClickListener {
            btnValidateInputClick()
        }
        mDataViewModel.mBlogResponse.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    hideProgressDialog()
                    context?.toast(getString(R.string.record_save_successfully))
                    clearText()
                }
                ResponseStatus.FAIL -> {
                    hideProgressDialog()
                    context?.toast(getString(R.string.serviceFailureError))
                }
                ResponseStatus.LOADING ->
                    showProgressDialog()
            }
        })
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* view.findViewById<Button>(R.id.button_second).setOnClickListener {
             findNavController().navigate(R.id.action_AddEmployee_to_EmployeeInformation)
         }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //  menu.findItem(R.id.action_EmployeeInformation_to_AddEmployee).isVisible = false
    }

    private fun clearText() {
        textFieldName.editText!!.setText("")
        textFieldSalary.editText!!.setText("")
        textFieldAge.editText!!.setText("")
    }

    private fun btnValidateInputClick() {
        val strName = textFieldName.editText!!.text.toString()
        val strSalary = textFieldSalary.editText!!.text.toString()
        val strAge = textFieldAge.editText!!.text.toString()
        when {
            TextUtils.isEmpty(strName) -> {
                textFieldName.error = "Input required"
                textFieldName.isErrorEnabled = true
            }
            TextUtils.isEmpty(strSalary) -> {
                textFieldSalary.error = "Input required"
                textFieldSalary.isErrorEnabled = true
            }
            TextUtils.isEmpty(strAge) -> {
                textFieldAge.error = "Input required"
                textFieldAge.isErrorEnabled = true
            }
            else -> {
                showProgressDialog()
                var mModelEmployeeRegistration =
                    ModelEmployeeRegistration(strName, strSalary, strAge)
                mDataViewModel.insertEmployee(mModelEmployeeRegistration)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupProgressDialog() {
        builder = AlertDialog.Builder(activity)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    private fun showProgressDialog() {
        if (dialog != null && !dialog.isShowing) {
            dialog.show()
        }
    }

    private fun hideProgressDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.hide()
            dialog.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog.isShowing) {
            dialog.hide()
            dialog.dismiss()
        }
    }

}
