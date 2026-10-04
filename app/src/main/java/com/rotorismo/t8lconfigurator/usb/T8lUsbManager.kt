package com.rotorismo.t8lconfigurator.usb

import android.content.Context
import android.hardware.usb.UsbManager
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProtos
import java.io.IOException
import java.nio.charset.StandardCharsets

class T8lUsbManager(private val context: Context) {

    private var serialPort: UsbSerialPort? = null
    private val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager

    fun connect(): Boolean {
        val availableDrivers = UsbSerialProtos.getDefaultProbeTable().findAllDrivers(usbManager)
        if (availableDrivers.isEmpty()) return false

        val driver = availableDrivers[0]
        val connection = usbManager.openDevice(driver.device) ?: return false

        return try {
            val port = driver.ports[0]
            port.open(connection)
            port.setParameters(115200, 8, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
            this.serialPort = port
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun openWiFi() {
        sendCommand("wifi open\n")
    }

    fun closeWiFi() {
        sendCommand("wifi close\n")
    }

    fun querySettings() {
        sendCommand("query\n")
    }

    private fun sendCommand(command: String) {
        serialPort?.let { port ->
            try {
                val bytes = command.toByteArray(StandardCharsets.UTF_8)
                port.write(bytes, 1000)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun disconnect() {
        try {
            serialPort?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            serialPort = null
        }
    }
}
