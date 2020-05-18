package com.techm.employeeManagement.model

import com.techm.employeeManagement.utils.ResponseStatus
/**
 * This class for handle API response
 * */
class ModelEmployeeServerResponse(
    var error: String,
    var status: ResponseStatus,
    var data: ModelEmployeeRegistration
)