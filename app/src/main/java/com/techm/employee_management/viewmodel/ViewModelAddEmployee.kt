package com.techm.employee_management.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.employee_management.callbacks.ResponseCallback
import com.techm.employee_management.model.ModelEmployeeRegistration
import com.techm.employee_management.model.ModelEmployeeServerResponse
import com.techm.employee_management.repository.RepositoryViewModel
import com.techm.employee_management.utils.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelAddEmployee(@NotNull application: Application) :
    AndroidViewModel(application), ResponseCallback<ModelEmployeeServerResponse> {
    private var repositoryViewModel: RepositoryViewModel = RepositoryViewModel()
    var mEmployeeResponse: MutableLiveData<ModelEmployeeServerResponse> =
        MutableLiveData<ModelEmployeeServerResponse>()
    /**
     * Calling API
     */
    fun insertEmployee(employeeInformation: ModelEmployeeRegistration) {
        repositoryViewModel.insertEmployee(employeeInformation, this)
    }

    /**
     * API success response
     * */

    override fun onSuccess(data: ModelEmployeeServerResponse) {
        mEmployeeResponse.value = data
    }

    /**
     * API failure response
     * */
    override fun onError(error: String?) {
        mEmployeeResponse.value =
            ModelEmployeeServerResponse(
                error.toString(),
                ResponseStatus.FAIL,
                ModelEmployeeRegistration("", "", "")
            )
    }


}