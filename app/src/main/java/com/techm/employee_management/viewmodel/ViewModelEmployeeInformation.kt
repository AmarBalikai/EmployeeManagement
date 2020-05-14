package com.techm.employee_management.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.employee_management.callbacks.ResponseCallback
import com.techm.employee_management.model.ModelServerResponse
import com.techm.employee_management.repository.RepositoryViewModel
import com.techm.employee_management.utils.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelEmployeeInformation(@NotNull application: Application) :
    AndroidViewModel(application), ResponseCallback {
    private var repositoryViewModel: RepositoryViewModel = RepositoryViewModel()
    var mBlogResponse: MutableLiveData<ModelServerResponse> =
        MutableLiveData<ModelServerResponse>()
    var mBlogResponseStatus: MutableLiveData<ModelServerResponse> =
        MutableLiveData<ModelServerResponse>()

    /**
     * one time initialize
     * */
    init {
        mBlogResponse.value = ModelServerResponse(ArrayList(), "", ResponseStatus.LOADING)
        //mBlogResponseStatus.value = ModelServerResponse(ArrayList(), "", ResponseStatus.LOADING)
        repositoryViewModel.retrieveBlogData(this)
    }

    /**
     * Calling API
     */
    fun getBlogInformation() {
        repositoryViewModel.retrieveBlogData(this)
    }

    /**
     * API success response
     * */

    override fun onSuccess(data: ModelServerResponse?) {
        mBlogResponse.value = data
        //mBlogResponseStatus.value = ModelServerResponse(ArrayList(), "", ResponseStatus.SUCCESS)
    }

    /**
     * API failure response
     * */
    override fun onError(error: String?) {
        mBlogResponse.value = ModelServerResponse(ArrayList(), error.toString(), ResponseStatus.FAIL)
        /*mBlogResponseStatus.value =
            ModelServerResponse(ArrayList(), error.toString(), ResponseStatus.FAIL)*/
    }
}