package com.example.remedialucp2_083.view.route

import com.example.remedialucp2_083.R
import com.example.remedialucp2_083.view.route.DestinasiNavigasi

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_buku
    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}