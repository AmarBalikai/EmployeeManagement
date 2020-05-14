package com.techm.employee_management.callbacks

import androidx.lifecycle.MutableLiveData
import com.techm.employee_management.model.ModelServerResponse

interface ResponseCallback
{
    fun onSuccess(data: ModelServerResponse?)
    fun onError(error:String?)
}