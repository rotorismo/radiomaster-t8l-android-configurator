package com.rotorismo.t8lconfigurator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rotorismo.t8lconfigurator.usb.T8lUsbManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(usbManager: T8lUsbManager) {
    var isConnected by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rotorismo T8L Configurator") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Status Kaart
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (isConnected) "Status: Verbonden" else "Status: Niet verbonden",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { isConnected = usbManager.connect() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isConnected) "Opnieuw verbinden" else "Verbinden via USB")
                    }
                }
            }

            // Wi-Fi Instellingen Kaart
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "ExpressLRS Wi-Fi Modus",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { usbManager.openWiFi() },
                            enabled = isConnected,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Open WiFi")
                        }

                        Button(
                            onClick = { usbManager.closeWiFi() },
                            enabled = isConnected,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text("Close WiFi")
                        }
                    }
                }
            }
        }
    }
}
