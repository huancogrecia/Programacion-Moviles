package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(
    onMedicoClick: (String, String, String) -> Unit,
    onMisCitasClick: () -> Unit,
    onHistorialClick: () -> Unit
) {

    val especialidades = listOf(
        "Cardiología",
        "Pediatría"
    )

    val medicos = listOf(
        Triple("Dra. Ana Torres", "Cardiología", "4.8"),
        Triple("Dr. Luis Vega", "Pediatría", "4.7"),
        Triple("Dra. Rosa Díaz", "Dermatología", "4.2")
    )

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {

                Text(
                    text = "Clínica Salud+",
                    modifier = Modifier.padding(16.dp)
                )

                TextButton(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                ) {
                    Text(text = "Inicio")
                }

                TextButton(
                    onClick = {
                        onMisCitasClick()
                    }
                ) {
                    Text(text = "Mis citas")
                }

                TextButton(
                    onClick = {
                        onHistorialClick()
                    }
                ) {
                    Text(text = "Historial médico")
                }
            }
        }
    ) {

        Scaffold(
            topBar = {

                TopAppBar(
                    title = {
                        Text(text = "Clínica Salud+")
                    },
                    navigationIcon = {

                        TextButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Text(text = "☰")
                        }
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Hola, Grecia Huanco",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(text = "Especialidades")

                LazyRow {
                    items(especialidades) { especialidad ->

                        Button(
                            onClick = { }
                        ) {
                            Text(text = especialidad)
                        }
                    }
                }

                Text(
                    text = "Médicos disponibles",
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 8.dp
                    )
                )

                LazyColumn {
                    items(medicos) { medico ->

                        Button(
                            onClick = {
                                onMedicoClick(
                                    medico.first,
                                    medico.second,
                                    medico.third
                                )
                            }
                        ) {
                            Text(
                                text = "${medico.first} - ${medico.second} - ${medico.third}"
                            )
                        }
                    }
                }
            }
        }
    }
}