package com.techm.employee_management.callbacks

import androidx.lifecycle.MutableLiveData
import com.techm.employee_management.model.ModelServerResponse
/**
 * This interface for creating a callbacks of API response
 * */
interface ResponseCallback<T>
{
    /**
     * @param T is a generic parameter
     * */
    fun onSuccess(data: T)
    fun onError(error:String?)
}