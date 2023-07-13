package com.weha.epsonthermal.domain.repository

import io.reactivex.rxjava3.core.Single

interface ESCPOSThermalRepository {
    fun print(): Single<String>
}