package com.weha.epsonthermal.domain.usecase

import com.weha.epsonthermal.domain.repository.ESCPOSThermalRepository

class ESCPOSThermalPrintUseCase(private val escposThermalRepository: ESCPOSThermalRepository) {
    fun execute() = escposThermalRepository.print()
}