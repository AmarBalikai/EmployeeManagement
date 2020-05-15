package com.techm.employee_management.model

import com.techm.employee_management.utils.ResponseStatus
/**
 * This class for handle API response
 * */
class ModelEmployeeServerResponse(
    var error: String,
    var status: ResponseStatus,
    var data: ModelEmployeeRegistration
)