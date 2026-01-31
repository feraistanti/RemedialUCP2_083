package com.example.remedialucp2_083.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedialucp2_083.view.route.DestinasiEdit
import com.example.remedialucp2_083.viewmodel.EditViewModel
import com.example.remedialucp2_083.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBukuScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BukuTopAppBar(
                title = stringResource(DestinasiEdit.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBukuBody(
            uiStateBuku = viewModel.uiStateBuku,
            onBukuValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editSatuBuku()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}