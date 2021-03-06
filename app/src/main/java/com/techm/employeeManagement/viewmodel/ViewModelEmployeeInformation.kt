package com.techm.employeeManagement.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.employeeManagement.callbacks.ResponseCallback
import com.techm.employeeManagement.model.ModelDeleteEmployee
import com.techm.employeeManagement.model.ModelServerResponse
import com.techm.employeeManagement.repository.RepositoryViewModel
import com.techm.employeeManagement.utils.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelEmployeeInformation(@NotNull application: Application) :
    AndroidViewModel(application), ResponseCallback<Any> {
    private var repositoryViewModel: RepositoryViewModel = RepositoryViewModel()
    var mEmployeeInformationData: MutableLiveData<ModelServerResponse> =
        MutableLiveData<ModelServerResponse>()

    var mEmployeeDeleteStatus: MutableLiveData<ModelDeleteEmployee> =
        MutableLiveData<ModelDeleteEmployee>()

    /**
     * one time initialize
     * */
    init {
        mEmployeeInformationData.value =
            ModelServerResponse(ArrayList(), "", ResponseStatus.LOADING)
        repositoryViewModel.retrieveBlogData(this)
    }

    /**
     * Calling API
     */
    fun getEmployeeInformation() {
        repositoryViewModel.retrieveBlogData(this)
    }

    /**
     * Delete employee API
     * */
    fun deleteEmployee(employeeId: String) {
        repositoryViewModel.deleteEmployee(employeeId, this)
    }

    /**
     * API success response
     * */

    override fun onSuccess(data: Any) {
        if (data is ModelServerResponse) {
            mEmployeeInformationData.value = data
        } else if (data is ModelDeleteEmployee) {
            mEmployeeDeleteStatus.value = data
        }
    }

    /**
     * API failure response
     * */
    override fun onError(error: String?) {
        mEmployeeInformationData.value =
            ModelServerResponse(ArrayList(), error.toString(), ResponseStatus.FAIL)
    }


}