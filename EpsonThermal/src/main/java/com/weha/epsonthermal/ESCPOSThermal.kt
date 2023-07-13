package com.weha.epsonthermal

import com.weha.epsonthermal.domain.repositoryImpl.ESCPOSThermalRepositoryImpl
import com.weha.epsonthermal.domain.usecase.ESCPOSThermalPrintUseCase

class ESCPOSThermal {

    companion object {
        val instance: ESCPOSThermal by lazy {
            ESCPOSThermal()
        }
    }

    fun print() = ESCPOSThermalPrintUseCase(ESCPOSThermalRepositoryImpl()).execute()

}