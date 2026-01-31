package com.example.remedialucp2_083.modeldata


data class Kategori(
    val id: Long = 0,
    val namaKategori: String = "",
    val parentId: Long = 0,
    val status: String = ""
)

data class DetailKategori(
    val id: Long = 0,
    val namaKategori: String = "",
    val status: String = ""
)

fun DetailKategori.toDataKategori(): Kategori = Kategori(
    id = id,
    namaKategori = namaKategori,
    status = status
)

fun Kategori.toDetailKategori(): DetailKategori = DetailKategori(
    id = id,
    namaKategori = namaKategori,
    status = status
)

data class UIStateKategori(
    val detailKategori: DetailKategori = DetailKategori(),
    val isEntryValid: Boolean = false
)

fun Kategori.toUiStateKategori(
    isEntryValid: Boolean = false): UIStateKategori = UIStateKategori(
    detailKategori = this.toDetailKategori(),
    isEntryValid = isEntryValid
)
