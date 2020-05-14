package com.techm.employee_management.callbacks

import androidx.lifecycle.MutableLiveData
import com.techm.employee_management.model.ModelServerResponse

interface ResponseCallback<T>
{

    fun onSuccess(data: T)
    fun onError(error:String?)
}