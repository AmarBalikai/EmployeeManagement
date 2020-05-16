package com.techm.employee_management.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvmbloginformation.network.ApiInterface
import com.techm.employee_management.model.ModelServerResponse
import com.techm.employee_management.repository.RepositoryViewModel
/*
import com.techmahidra.telstrademo.data.network.APIService
import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.data.response.FeatureResponse
*/
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Spy

import org.junit.Assert.*
import org.mockito.*
import org.mockito.Mockito.*
import java.net.SocketException

@RunWith(JUnit4::class)
class ViewModelEmployeeInformationTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var mApiInterface: ApiInterface

    lateinit var mViewModelEmployeeInformation: ViewModelEmployeeInformation

    lateinit var mRepositoryViewModel: RepositoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mRepositoryViewModel = RepositoryViewModel()
        val application = Mockito.mock(Application::class.java)
        this.mViewModelEmployeeInformation = ViewModelEmployeeInformation(application)
    }

    @Test
    fun test_getEmployeeInformationAPISuccess() {
        `when`(this.mApiInterface.getEmployeeInformation()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<RepositoryViewModel>())
        }

        val observer = mock(Observer::class.java) as Observer<ModelServerResponse>
        this.mViewModelEmployeeInformation.mEmployeeInformationData.observeForever(observer)

        this.mViewModelEmployeeInformation.getEmployeeInformation()

        assertNotNull(this.mViewModelEmployeeInformation.mEmployeeInformationData.value)
    }

    @Test
    fun test_getEmployeeInformationError() {
        `when`(this.mApiInterface.getEmployeeInformation()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = mock(Observer::class.java) as Observer<ModelServerResponse>
        this.mViewModelEmployeeInformation.mEmployeeInformationData.observeForever(observer)

        this.mViewModelEmployeeInformation.getEmployeeInformation()

        assertNotNull(this.mViewModelEmployeeInformation.mEmployeeInformationData.value)
        assert(this.mViewModelEmployeeInformation.mEmployeeInformationData.value?.errorMessage is String)
    }
}