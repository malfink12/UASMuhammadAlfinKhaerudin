package com.example.uasmuhammadalfinkhaerudin.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.uasmuhammadalfinkhaerudin.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    id: Int,
    mainViewModel: MainViewModel,
    onBack:() -> Unit
) {
    val jadwal = mainViewModel.note.jadwal
    val jam = mainViewModel.note.jam
    val hari = mainViewModel.note.hari

    LaunchedEffect(
        key1 = true,
        block = {
            mainViewModel.getNoteById(id = id)
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Ubah jadwal"
                )
            },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Batal ubah"
                        )
                    }
                }
            )
        },
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            TextField(
                value = jadwal,
                onValueChange = {newValue ->
                    mainViewModel.updateJadwal(newValue = newValue)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                label = {
                    Text(
                        text = "Jadwal"
                    )
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Words
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                value = hari,
                onValueChange = {newValue ->
                    mainViewModel.updateHari(newValue = newValue)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                label = {
                    Text(
                        text = "Hari"
                    )
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Words
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                value = jam,
                onValueChange = {newValue ->
                    mainViewModel.updateJam(newValue = newValue)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                label = {
                    Text(
                        text = "Jam"
                    )
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Words
                )
            )
            Button(onClick = {
                mainViewModel.updateNote(mainViewModel.note)
                onBack()
            }) {
                Text(
                    text = "Perbarui Jadwal"
                )
            }
        }
    }
}