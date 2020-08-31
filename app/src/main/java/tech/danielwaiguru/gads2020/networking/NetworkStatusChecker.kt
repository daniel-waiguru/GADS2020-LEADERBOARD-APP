package tech.danielwaiguru.gads2020.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {
    @RequiresApi(Build.VERSION_CODES.M)
    inline fun performIfConnectedToInternet(actionNotConnected: () -> Unit, action: () -> Unit){
        if (hasConnection()){
            action()
        }
        else {
            actionNotConnected()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}