package com.techm.employee_management.view

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techm.employee_management.R
import com.techm.employee_management.adapter.AdapterBlog
import com.techm.employee_management.model.ModelEmployeeInformation
import com.techm.employee_management.utils.*
import com.techm.employee_management.viewmodel.ViewModelEmployeeInformation
import kotlinx.android.synthetic.main.fragment_employee_information.*
import kotlinx.android.synthetic.main.fragment_employee_information.view.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class EmployeeInformation : Fragment(), View.OnClickListener {

    private lateinit var mDataViewModel: ViewModelEmployeeInformation
    private lateinit var mAdapter: AdapterBlog
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var isSwipeToRefreshCall: Boolean = false
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mEmployeeList = ArrayList<ModelEmployeeInformation>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_employee_information, container, false)
        activity?.title = getString(R.string.employee_information)
        mDataViewModel = ViewModelProvider(this).get(ViewModelEmployeeInformation::class.java)
        setupProgressDialog()
        view.searchView.queryHint = getString(R.string.search)

        view.swipeToRefresh.setOnRefreshListener {
            isSwipeToRefreshCall = true
            getCountryFeaturesData(context)
        }

        /*view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let { mAdapter.filter(it) }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })*/
        view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.i("Search","Press querysubmit")

                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("Search","Press querytextchange")
                newText?.let { mAdapter.filter(it) }
                return true
            }
        })
        /**
         * Setting blank adapter for initialize
         */
        view.country_list.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = AdapterBlog(ArrayList(), context)
        linearLayoutManager = LinearLayoutManager(activity)
        view.country_list.layoutManager = linearLayoutManager
        view.country_list.adapter = mAdapter

        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var mModelEmployeeInformation =
                    mAdapter.getItemAtPosition(viewHolder.adapterPosition)
                mAdapter.removeAt(viewHolder.adapterPosition)
                mDataViewModel.deleteEmployee(mModelEmployeeInformation.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(view.country_list)

        /**
         * API Live data observer
         * */
        mDataViewModel.mEmployeeInformationData.observe(viewLifecycleOwner, Observer {
            if (isSwipeToRefreshCall) {
                isSwipeToRefreshCall = false
                swipeToRefresh.isRefreshing = false
            } else
                hideProgressDialog()

            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    mEmployeeList.clear()
                    mEmployeeList = it.data
                    mAdapter.setList(it.data)
                }
                ResponseStatus.FAIL ->
                    context?.toast(getString(R.string.serviceFailureError))
                ResponseStatus.LOADING ->
                    showProgressDialog()
            }
        })

        mDataViewModel.mEmployeeDeleteStatus.observe(viewLifecycleOwner, Observer {
            when (it.apiStatus) {
                ResponseStatus.SUCCESS -> {
                    context?.toast(getString(R.string.employee_deleted_successfully))

                }
                ResponseStatus.FAIL -> {
                    context?.toast(getString(R.string.serviceFailureError))
                    //mAdapter.setList(mEmployeeList)
                }
                ResponseStatus.LOADING ->
                    showProgressDialog()
            }
        })
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*button_first.setOnClickListener {
            findNavController().navigate(R.id.action_EmployeeInformation_to_AddEmployee)
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     *  Handle action bar item clicks here
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_add -> {
                findNavController().navigate(R.id.action_EmployeeInformation_to_AddEmployee)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This method for get data from the viewModel
     */
    private fun getCountryFeaturesData(context: Context?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (NetworkConnection.isNetworkConnected()) {
                showProgressDialog()
                mDataViewModel.getBlogInformation()
            } else {
                if (swipeToRefresh.isRefreshing) {
                    swipeToRefresh.isRefreshing = false
                }
                context?.toast(getString(R.string.device_not_connected_to_internet))
            }
        } else {
            if (NetworkConnection.isNetworkConnectedKitkat()) {
                showProgressDialog()
                mDataViewModel.getBlogInformation()

            } else {
                if (swipeToRefresh.isRefreshing) {
                    swipeToRefresh.isRefreshing = false
                }
                context?.toast(getString(R.string.device_not_connected_to_internet))
            }
        }
    }

    /**
     * Showing dialog when api call
     */
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

    override fun onResume() {
        super.onResume()
        getCountryFeaturesData(context)
    }

    override fun onClick(v: View?) {

    }

}
