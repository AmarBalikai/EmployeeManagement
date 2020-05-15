package com.techm.employee_management.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.employee_management.callbacks.ResponseCallback
import com.techm.employee_management.model.ModelEmployeeRegistration
import com.techm.employee_management.model.ModelEmployeeServerResponse
import com.techm.employee_management.model.ModelServerResponse
import com.techm.employee_management.repository.RepositoryViewModel
import com.techm.employee_management.utils.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelAddEmployee(@NotNull application: Application) :
    AndroidViewModel(application), ResponseCallback<ModelEmployeeServerResponse> {
    private var repositoryViewModel: RepositoryViewModel = RepositoryViewModel()
    var mBlogResponse: MutableLiveData<ModelEmployeeServerResponse> =
        MutableLiveData<ModelEmployeeServerResponse>()
    /*var mBlogResponseStatus: MutableLiveData<ModelServerResponse> =
        MutableLiveData<ModelServerResponse>()
*/
    /**
     * one time initialize
     * */
    /*init {
        mBlogResponse.value = ModelEmployeeServerResponse(  ResponseStatus.LOADING,ArrayList())
        repositoryViewModel.retrieveBlogData(this)
    }*/

    /**
     * Calling API
     */
    fun insertEmployee(employeeInformation: ModelEmployeeRegistration) {
        repositoryViewModel.insertEmployee(employeeInformation, this)
    }

    /**
     * API success response
     * */

    /*override fun onSuccess(data: ModelServerResponse?) {
        mBlogResponse.value = data
        //mBlogResponseStatus.value = ModelServerResponse(ArrayList(), "", ResponseStatus.SUCCESS)
    }*/
    /*override fun onSuccess(data: T) {
        mBlogResponse.value = data
    }*/
    override fun onSuccess(data: ModelEmployeeServerResponse) {
        mBlogResponse.value = data
    }

    /**
     * API failure response
     * */
    override fun onError(error: String?) {
        mBlogResponse.value =
            ModelEmployeeServerResponse(
                error.toString(),
                ResponseStatus.FAIL,
                ModelEmployeeRegistration("", "", "")
            )
        /*mBlogResponseStatus.value =
            ModelServerResponse(ArrayList(), error.toString(), ResponseStatus.FAIL)*/
    }


}