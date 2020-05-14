package com.techm.employee_management.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.techm.employee_management.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddEmployee : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_employee, container, false)
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

}
