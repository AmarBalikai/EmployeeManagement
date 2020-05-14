package com.techm.employee_management.model

import com.techm.employee_management.utils.ResponseStatus

class ModelServerResponse(var data:ArrayList<ModelEmployeeInformation> ,var errorMessage:String="",var status: ResponseStatus)