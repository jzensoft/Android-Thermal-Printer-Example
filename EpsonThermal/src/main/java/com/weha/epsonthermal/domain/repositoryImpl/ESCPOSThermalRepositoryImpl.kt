package com.weha.epsonthermal.domain.repositoryImpl

import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.tcp.TcpConnection
import com.weha.epsonthermal.domain.repository.ESCPOSThermalRepository
import io.reactivex.rxjava3.core.Single

class ESCPOSThermalRepositoryImpl : ESCPOSThermalRepository {
    override fun print(): Single<String> {
        return Single.create {
            try {
                val tcpConnection = TcpConnection("192.168.1.37", 9100, 60)
                val printer = EscPosPrinter(tcpConnection, 203, 48f, 32)
                if (tcpConnection.isConnected) {
                    printer.printFormattedText("[C]Demo")
                    printer.disconnectPrinter()
                    it.onSuccess("Printer")
                } else {
                    it.tryOnError(Throwable("Not found printer, please check connection and try again"))
                }
            } catch (e: Exception) {
                it.tryOnError(e)
            }
        }
    }
}