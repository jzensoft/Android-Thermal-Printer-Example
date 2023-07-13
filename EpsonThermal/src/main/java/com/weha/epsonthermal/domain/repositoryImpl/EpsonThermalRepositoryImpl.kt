package com.weha.epsonthermal.domain.repositoryImpl

import android.content.Context
import android.util.Log
import com.epson.epos2.printer.Printer
import com.weha.epsonthermal.domain.repository.EpsonThermalRepository
import io.reactivex.rxjava3.core.Single

class EpsonThermalRepositoryImpl(private val application: Context) : EpsonThermalRepository {

    private val TAG = EpsonThermalRepositoryImpl::class.simpleName

    override fun print(): Single<String> {
        return Single.create {
            var printer: Printer? = null
            try {
                printer = Printer(Printer.TM_T88, Printer.MODEL_CHINESE, application)
                printer.connect("TCP:192.168.1.37", Printer.PARAM_DEFAULT)
                val status = printer.status
                if (status.connection != Printer.TRUE) {
                    disConnect(printer)
                    it.tryOnError(Throwable("Not found printer"))
                } else {
                    printer.clearCommandBuffer()
                    printer.beginTransaction()
                    printer.addText("ABCD")
                    printer.addFeedLine(5)
                    //printer.addCut(Printer.CUT_NO_FEED)
                    printer.sendData(Printer.PARAM_DEFAULT)
                    printer.endTransaction()
                }

                printer.setReceiveEventListener { p, i, info, s ->
                    Log.d(TAG, "$info")
                    disConnect(printer)
                    it.onSuccess("Printed")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                disConnect(printer)
                it.tryOnError(e)
            }
        }
    }

    private fun disConnect(printer: Printer?) {
        printer?.disconnect()
    }
}