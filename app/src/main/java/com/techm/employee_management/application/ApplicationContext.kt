package com.techm.employee_management.application

import android.app.Application
import android.content.Context
import com.techm.employee_management.utils.toast

/**
 * This class for creating creating application context
 */
class ApplicationContext : Application() {

    companion object {
        lateinit var context: Context
    }

    /**
     * This method is used to initialize the context
     */
    override fun onCreate() {
        super.onCreate()
        context = this

    }
}