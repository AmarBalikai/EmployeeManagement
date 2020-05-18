package com.example.mvvmbloginformation.network

import com.techm.employeeManagement.model.ModelDeleteEmployee
import com.techm.employeeManagement.model.ModelEmployeeRegistration
import com.techm.employeeManagement.model.ModelEmployeeServerResponse
import com.techm.employeeManagement.model.ModelServerResponse
import com.techm.employeeManagement.utils.Constant
import com.techm.employeeManagement.utils.Constant.Companion.deleteEmployee
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    /**
     * This method is getting for list's of objects from server
     */
    @GET(Constant.employeeInformationUrl)
    fun getEmployeeInformation(): Call<ModelServerResponse>

    /**
     * This method for insert employee
     * */
    @POST(Constant.insertEmployee)
    fun insertEmployee(@Body body: ModelEmployeeRegistration): Call<ModelEmployeeServerResponse>

    /**
     * This method for delete employee
     * */
    @DELETE(deleteEmployee)
    fun deleteEmployee(@Path("id") id: String): Call<ModelDeleteEmployee>
}