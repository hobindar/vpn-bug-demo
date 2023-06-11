package ca.hobin.bugdemo

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

private const val VPN_PACKAGE_NAME = "com.cloudflare.onedotonedotonedotone"

class MainActivity : AppCompatActivity() {
    private val adminComponent by lazy { ComponentName(this, DeviceAdmin::class.java) }
    private val devicePolicyManager by lazy { getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager }
    private val setVpnWithLockdown: View by lazy { findViewById(R.id.set_vpn_with_lockdown) }
    private val setVpnWithoutLockdown: View by lazy { findViewById(R.id.set_vpn_without_lockdown) }
    private val disableVpn: View by lazy { findViewById(R.id.disable_vpn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setVpnWithLockdown.setOnClickListener { setAlwaysOnVpn(true) }
        setVpnWithoutLockdown.setOnClickListener { setAlwaysOnVpn(false) }
        disableVpn.setOnClickListener { disableAlwaysOnVpn() }
    }

    private fun disableAlwaysOnVpn() {
        devicePolicyManager.setAlwaysOnVpnPackage(
            adminComponent,
            null,
            false,
            setOf(packageName)
        )
    }

    private fun setAlwaysOnVpn(lockdownEnabled: Boolean) {
        devicePolicyManager.setAlwaysOnVpnPackage(
            adminComponent,
            VPN_PACKAGE_NAME,
            lockdownEnabled,
            setOf(packageName)
        )
    }
}
