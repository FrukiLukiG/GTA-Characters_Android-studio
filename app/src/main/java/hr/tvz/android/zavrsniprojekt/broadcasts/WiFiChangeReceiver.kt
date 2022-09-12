package hr.tvz.android.zavrsniprojekt.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast

class WiFiChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val wifiStateExtra = intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)

        if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED) {
            Toast.makeText(context, "WiFi enabled", Toast.LENGTH_LONG).show()
        }
        else if (wifiStateExtra == WifiManager.WIFI_STATE_DISABLED) {
            Toast.makeText(context, "WiFi is disabled, you might need to enable it to see full content", Toast.LENGTH_LONG).show()
        }

    }
}