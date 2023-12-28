package com.example.uasmuhammadalfinkhaerudin.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.uasmuhammadalfinkhaerudin.TopAppBar
import com.example.uasmuhammadalfinkhaerudin.presentation.MainViewModel
import java.util.Calendar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uasmuhammadalfinkhaerudin.domain.model.Note
import com.example.uasmuhammadalfinkhaerudin.presentation.home.common.mySnackBar
import com.example.uasmuhammadalfinkhaerudin.presentation.home.components.AlertHomeScreen
import com.example.uasmuhammadalfinkhaerudin.presentation.home.components.EmptyNoteScrenn
import com.example.uasmuhammadalfinkhaerudin.presentation.home.components.NoteCard
import kotlinx.coroutines.launch
import com.example.uasmuhammadalfinkhaerudin.ui.theme.Warna

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onUpdate:(id: Int) -> Unit
) {
    val notes by mainViewModel.getAllNotes.collectAsStateWithLifecycle(initialValue = emptyList())

    var openDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    var day by remember { mutableStateOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) }
    // Convert day to indonesian language
    val dayIndoVersion = when (day) {
        Calendar.MONDAY -> "Senin"
        Calendar.TUESDAY -> "Selasa"
        Calendar.WEDNESDAY -> "Rabu"
        Calendar.THURSDAY -> "Kamis"
        Calendar.FRIDAY -> "Jumat"
        Calendar.SATURDAY -> "Sabtu"
        Calendar.SUNDAY -> "Minggu"
        else -> "Unknown"
    }
    val listDays = mapOf(
        Calendar.MONDAY to "Senin",
        Calendar.TUESDAY to "Selasa",
        Calendar.WEDNESDAY to "Rabu",
        Calendar.THURSDAY to "Kamis",
        Calendar.FRIDAY to "Jumat",
        Calendar.SATURDAY to "Sabtu",
        Calendar.SUNDAY to "Minggu"
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) {}},
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    // Kasih background color
                    .background(Color(android.graphics.Color.parseColor("#FFE6E6")))
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "japel.edu",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                )
                Text(
                    text = dayIndoVersion,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clickable { showBottomSheet = true }
                )
                // Buat button tambah
                Button(
                    onClick = { openDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Magenta,
                        contentColor = Color.White
                    ),
                    // Kecilin buttonnya
                    modifier = Modifier.height(30.dp),
                    shape = CutCornerShape(percent = 25)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            }
        }
    ) { paddingValues: PaddingValues ->
        AlertHomeScreen(
            openDialog = openDialog,
            onClose = { openDialog = false },
            mainViewModel = mainViewModel
        )

        // Buat filter dari hari yang di klik lalu taro di list
        var filteredNotes = listOf<Note>()
        for (note in notes) {
            if (note.hari == dayIndoVersion) {
                filteredNotes += note
            }
        }
        if (filteredNotes.isEmpty()) {
            EmptyNoteScrenn(paddingValues = paddingValues)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items (
                    items = filteredNotes,
                    key = { it.id }
                ) {note ->
                    var color = Color(0xFFFFABE1)
                    if (note.id%2 == 0){
                        color = Color(0xFFFFE6E6)
                    }
                    NoteCard(
                        note = note,
                        onDone = {
                            mainViewModel.deleteNote(note = note)
                            mySnackBar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                msg = "Jadwal terhapus"
                            )
                        },
                        color = color,
                        onUpdate = onUpdate
                    )
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                ) {
                    listDays.forEach { (key, value) ->
                        Box(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                        ) {
                            Button(
                                onClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                        day = key
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Magenta,
                                    contentColor = Color.White
                                ),
                                // Kecilin buttonnya
                                modifier = Modifier
                                    .height(30.dp),
                                shape = CutCornerShape(percent = 25),
                            ) {
                                Text(text = value)
                            }
                        }
                    }
                }
            }
        }
    }
}