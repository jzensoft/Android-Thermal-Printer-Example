package com.weha.epsonthermal.domain.usecase

import com.weha.epsonthermal.domain.repository.EpsonThermalRepository

class EpsonThermalPrintUseCase(private val epsonThermalRepository: EpsonThermalRepository) {
    fun execute() = epsonThermalRepository.print()
}