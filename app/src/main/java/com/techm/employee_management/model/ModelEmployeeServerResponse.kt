package com.techm.employee_management.model

import com.techm.employee_management.utils.ResponseStatus

class ModelEmployeeServerResponse(
    var error: String,
    var status: ResponseStatus,
    var data: ModelEmployeeRegistration
)