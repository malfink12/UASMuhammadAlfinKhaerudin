package com.example.uasmuhammadalfinkhaerudin.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.uasmuhammadalfinkhaerudin.domain.model.Note
import com.example.uasmuhammadalfinkhaerudin.presentation.MainViewModel
import com.example.uasmuhammadalfinkhaerudin.presentation.home.common.toastMsg
import kotlinx.coroutines.job

@Composable
fun AlertHomeScreen(
    openDialog: Boolean,
    onClose:() -> Unit,
    mainViewModel: MainViewModel
) {
    var jadwal by remember { mutableStateOf("") }
    var jam by remember { mutableStateOf("") }
    var hari by remember { mutableStateOf("") }
    
    val note = Note(0, jadwal, jam, hari)
    
    val focusRequester = FocusRequester()
    val context = LocalContext.current
    
    if(openDialog) {
        AlertDialog(
            title = {
                Text(text = "Jadwal")
            },
            text = {
              LaunchedEffect(
                  key1 = true, 
                  block = {
                      coroutineContext.job.invokeOnCompletion { 
                          focusRequester.requestFocus()
                      }
                  }
              )
              Column(
                  modifier = Modifier.fillMaxWidth()
              ) {
                  TextField(
                      value = jadwal, 
                      onValueChange = { jadwal = it },
                      placeholder = {
                          Text(text = "Tambahkan jadwal")
                      },
                      shape = RectangleShape,
                      modifier = Modifier.focusRequester(focusRequester),
                      keyboardOptions = KeyboardOptions(
                          capitalization = KeyboardCapitalization.Words,
                          imeAction = ImeAction.Done
                      )
                  )
                  TextField(
                      value = jam,
                      onValueChange = { jam = it },
                      placeholder = {
                          Text(text = "Tambahkan jam")
                      },
                      shape = RectangleShape,
                      modifier = Modifier.focusRequester(focusRequester),
                      keyboardOptions = KeyboardOptions(
                          capitalization = KeyboardCapitalization.Words,
                          imeAction = ImeAction.Done
                      )
                  )
                  TextField(
                      value = hari,
                      onValueChange = { hari = it },
                      placeholder = {
                          Text(text = "Tambahkan hari")
                      },
                      shape = RectangleShape,
                      modifier = Modifier.focusRequester(focusRequester),
                      keyboardOptions = KeyboardOptions(
                          capitalization = KeyboardCapitalization.Words,
                          imeAction = ImeAction.Done
                      )
                  )
              }
            },
            onDismissRequest = {
                onClose()
                jadwal = ""
                hari = ""
                jam = ""
            }, 
            confirmButton = {
                Button(onClick = {
                    if(jadwal.isNotBlank() and jam.isNotBlank() and hari.isNotBlank()) {
                        mainViewModel.insertNote(note = note)
                        jadwal = ""
                        hari = ""
                        jam = ""
                        onClose()
                    } else if (jadwal.isBlank()) {
                        toastMsg(
                            context,
                            "Jadwal belum diisi"
                        )
                    } else if (jam.isBlank()) {
                        toastMsg(
                            context,
                            "Jam belum diisi"
                        )
                    } else if (hari.isBlank()) {
                        toastMsg(
                            context,
                            "Hari belum diisi"
                        )
                    }
                }) {
                    Text(text = "Simpan jadwal")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    onClose()
                    jadwal = ""
                    hari = ""
                    jam = ""
                }) {
                    Text(text = "Batalkan")
                }
            }
        )
    }
}