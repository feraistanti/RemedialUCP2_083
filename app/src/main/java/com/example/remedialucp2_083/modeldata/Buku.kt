package com.example.remedialucp2_083.modeldata


data class Buku(
    val id: Long = 0,
    val judul: String = "",
    val penulis: String = "",
    val statusPeminjaman: String = ""
)

data class DetailBuku(
    val id: Long = 0,
    val judul: String = "",
    val penulis: String = "",
    val statusPeminjaman: String = ""
)

fun DetailBuku.toDataBuku(): Buku = Buku(
    id = id,
    judul = judul,
    penulis = penulis,
    statusPeminjaman = statusPeminjaman
)

fun Buku.toDetailBuku(): DetailBuku = DetailBuku(
    id = id,
    judul = judul,
    penulis = penulis,
    statusPeminjaman = statusPeminjaman
)

data class UIStateBuku(
    val detailBuku: DetailBuku = DetailBuku(),
    val isEntryValid: Boolean = false
)

fun Buku.toUiStateBuku(isEntryValid: Boolean = false): UIStateBuku = UIStateBuku(
    detailBuku = this.toDetailBuku(),
    isEntryValid = isEntryValid
)
