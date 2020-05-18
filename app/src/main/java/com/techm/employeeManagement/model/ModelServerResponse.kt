package com.techm.employeeManagement.model

import com.techm.employeeManagement.utils.ResponseStatus

/**
 * This class is for handle server response
 * */
class ModelServerResponse(var data:ArrayList<ModelEmployeeInformation> ,var errorMessage:String="",var status: ResponseStatus)