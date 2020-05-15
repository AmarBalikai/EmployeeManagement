package com.example.mvvmbloginformation.network

import com.techm.employee_management.model.ModelDeleteEmployee
import com.techm.employee_management.model.ModelEmployeeRegistration
import com.techm.employee_management.model.ModelEmployeeServerResponse
import com.techm.employee_management.model.ModelServerResponse
import com.techm.employee_management.utils.Constant
import com.techm.employee_management.utils.Constant.Companion.deleteEmployee
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface
{
    /**
     * This method is getting for list's of objects from server
     */
    @GET(Constant.employeeInformationUrl)
    fun getEmployeeInformation(): Call<ModelServerResponse>


    @POST(Constant.insertEmployee)
    fun insertEmployee(@Body body: ModelEmployeeRegistration): Call<ModelEmployeeServerResponse>

    @DELETE(deleteEmployee)
    fun deleteEmployee(@Path("id") id: String): Call<ModelDeleteEmployee>
}