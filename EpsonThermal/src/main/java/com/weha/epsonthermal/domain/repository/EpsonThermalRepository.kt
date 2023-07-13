package com.weha.epsonthermal.domain.repository

import io.reactivex.rxjava3.core.Single

interface EpsonThermalRepository {
    fun print(): Single<String>
}