package com.techm.employee_management.model

import com.techm.employee_management.utils.ResponseStatus

/**
 * This class is for handle server response
 * */
class ModelServerResponse(var data:ArrayList<ModelEmployeeInformation> ,var errorMessage:String="",var status: ResponseStatus)