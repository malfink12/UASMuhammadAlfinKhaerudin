package com.example.uasmuhammadalfinkhaerudin.presentation.home.common

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun mySnackBar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    msg: String,
) {
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val snackbarResult = snackbarHostState.showSnackbar(
            message = msg,
            duration = SnackbarDuration.Short
        )
        when(snackbarResult) {
            SnackbarResult.ActionPerformed -> {
                SnackbarResult.Dismissed
            }
            SnackbarResult.Dismissed -> {}
        }
    }
}