package com.techm.employee_management.repository

import com.example.mvvmbloginformation.network.ApiClient
import com.techm.employee_management.callbacks.ResponseCallback
import com.techm.employee_management.model.ModelEmployeeRegistration
import com.techm.employee_management.model.ModelEmployeeServerResponse
import com.techm.employee_management.model.ModelServerResponse
import com.techm.employee_management.utils.ResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel {
    /**
     * Method for API call
     * */
    fun retrieveBlogData(objCallback: ResponseCallback<ModelServerResponse>) {
        var listResponse: ModelServerResponse;

        val data: Call<ModelServerResponse>? = ApiClient.build()?.getEmployeeInformation()
        val enqueue = data?.enqueue(object : Callback<ModelServerResponse> {
            override fun onResponse(
                call: Call<ModelServerResponse>,
                response: Response<ModelServerResponse>
            ) {
                if (response.isSuccessful) {

                    listResponse = response.body()!!
                    listResponse.status = ResponseStatus.SUCCESS
                    /**
                     * Send success response to viewModel using this callback
                     */
                    objCallback.onSuccess(listResponse)
                }
            }

            override fun onFailure(call: Call<ModelServerResponse>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onError(t.message)
            }
        })

    }

    fun insertEmployee(employeeData: ModelEmployeeRegistration, objCallback: ResponseCallback<ModelEmployeeServerResponse>) {
        var listResponse: ModelEmployeeServerResponse;
        val data: Call<ModelEmployeeServerResponse>? =
            ApiClient.build()?.insertEmployee(employeeData)
        val enqueue = data?.enqueue(object : Callback<ModelEmployeeServerResponse> {
            override fun onResponse(
                call: Call<ModelEmployeeServerResponse>,
                response: Response<ModelEmployeeServerResponse>
            ) {
                if (response.isSuccessful) {

                    listResponse = response.body()!!
                    listResponse.status = ResponseStatus.SUCCESS
                    /**
                     * Send success response to viewModel using this callback
                     */
                    objCallback.onSuccess(listResponse)
                }
            }

            override fun onFailure(call: Call<ModelEmployeeServerResponse>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onError(t.message)
            }
        })

    }
}
