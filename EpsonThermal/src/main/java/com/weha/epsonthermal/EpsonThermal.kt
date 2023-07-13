package com.weha.epsonthermal

import android.content.Context
import com.weha.epsonthermal.domain.repositoryImpl.EpsonThermalRepositoryImpl
import com.weha.epsonthermal.domain.usecase.EpsonThermalPrintUseCase

class EpsonThermal {

    private lateinit var application: Context

    companion object {
        val instance: EpsonThermal by lazy {
            EpsonThermal()
        }
    }

    fun initPrinter(application: Context) {
        this.application = application
    }

    fun print() = EpsonThermalPrintUseCase(EpsonThermalRepositoryImpl(application)).execute()
}