package com.edolo.retrofit.ui.view

import android.content.Intent
import android.net.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import com.edolo.retrofit.R
import com.edolo.retrofit.clases.ConnectionLiveData
import com.edolo.retrofit.databinding.ActivityMainBinding
import com.edolo.retrofit.model.ProductosM
import com.edolo.retrofit.retrofit.ApiStatus
import com.edolo.retrofit.ui.viewmodel.MainActivityVM
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vista: MainActivityVM by viewModels()
    private var networkRequest: NetworkRequest = getNetworkRequest()
    private var networkCallback: ConnectivityManager.NetworkCallback = getNetworkCallBack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vista.getInternet(this).observe(this) {
            if (it) {

                update()
            } else {

            }
        }


        binding.refresh.setOnRefreshListener {
            update()
            binding.refresh.isRefreshing = false
        }

        //      getConnectivityManager().registerNetworkCallback(networkRequest, networkCallback)

    }

    fun update() {
        vista.getData(this).observe(this) { result ->
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    val todo = result
                    val data = result.data
                    if (!todo.loading) {
                        data as List<ProductosM>
                       // binding.lottieCarga.removeAllAnimatorListeners()
                        binding.lottieCarga.clearAnimation()
                        binding.lottieCarga.setAnimation(R.raw.lottie_download)
                        Log.i("MENSAGE", "Datos:${data[0].category}")
                    }
                }
                ApiStatus.LOADING -> {
                    val data = result.loading
                    if (data) {
                        Log.i("MENSAGE", "Carga:${data}")
                    }
                }
                ApiStatus.ERROR -> {
                    val data = result.message

                    binding.lottieCarga.clearAnimation()
                    binding.lottieCarga.setAnimation(R.raw.lottie_sin_conexion)
                    Log.i("MENSAGE", "Error:${data}")
                }
                ApiStatus.INTERNET -> {

                }
            }

        }
    }

    private fun getNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)    //here!!
            .build()
    }

    private fun getNetworkCallBack(): ConnectivityManager.NetworkCallback {
        return object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {    //when Wifi is on
                super.onAvailable(network)
                Toast.makeText(this@MainActivity, "Wifi is on!", Toast.LENGTH_SHORT).show()

            }

            override fun onLost(network: Network) {    //when Wifi 【turns off】
                super.onLost(network)
                Toast.makeText(this@MainActivity, "Wifi turns off!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getConnectivityManager() = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onResume() {
        super.onResume()
        getConnectivityManager().registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onPause() {
        super.onPause()
        getConnectivityManager().unregisterNetworkCallback(networkCallback)
    }
}