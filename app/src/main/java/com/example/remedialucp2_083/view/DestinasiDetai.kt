package com.example.remedialucp2_083.view

import com.example.remedialucp2_083.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_buku"
    override val titleRes = R.string.detail_buku
    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}