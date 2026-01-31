package com.example.remedialucp2_083.view.route

import com.example.remedialucp2_083.R
import com.example.remedialucp2_083.view.route.DestinasiNavigasi

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_buku"
    override val titleRes = R.string.detail_buku
    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}