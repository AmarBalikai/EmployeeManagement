package com.example.mvvmbloginformation.network

import com.techm.employee_management.model.ModelServerResponse
import com.techm.employee_management.utils.Constant
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface
{
    /**
     * This method is getting for list's of objects from server
     */
    @GET(Constant.employeeInformationUrl)
    fun getEmployeeInformation(): Call<ModelServerResponse>
}