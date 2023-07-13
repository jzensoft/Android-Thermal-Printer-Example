package com.weha.printerexternal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.weha.epsonthermal.ESCPOSThermal
import com.weha.epsonthermal.EpsonThermal
import com.weha.printerexternal.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEpson.setOnClickListener {
            epsonPrinter()
        }
        binding.btnEsc.setOnClickListener {
            escPOSThermal()
        }
    }

    private fun escPOSThermal() {
        ESCPOSThermal.instance.print()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, e.message ?: " ")
                }

                override fun onSuccess(t: String) {
                    Log.d(TAG, t)
                }
            })
    }

    private fun epsonPrinter() {
        EpsonThermal.instance.print()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, e.message ?: " ")
                }

                override fun onSuccess(t: String) {
                    Log.d(TAG, t)
                }
            })
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}