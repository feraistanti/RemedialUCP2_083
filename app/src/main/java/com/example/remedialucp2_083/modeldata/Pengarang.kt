package com.example.remedialucp2_083.modeldata


data class Pengarang(
    val id: Long = 0,
    val namaPengarang: String = "",
    val asal: String = "",
    val status: String = ""
)

data class DetailPengarang(
    val id: Long = 0,
    val namaPengarang: String = "",
    val asal: String = "",
    val status: String = ""
)

fun DetailPengarang.toDataPengarang(): Pengarang = Pengarang(
    id = id,
    namaPengarang = namaPengarang,
    asal = asal,
    status = status
)

fun Pengarang.toDetailPengarang(): DetailPengarang = DetailPengarang(
    id = id,
    namaPengarang = namaPengarang,
    asal = asal,
    status = status
)

data class UIStatePengarang(
    val detailPengarang: DetailPengarang = DetailPengarang(),
    val isEntryValid: Boolean = false
)

fun Pengarang.toUiStatePengarang(
    isEntryValid: Boolean = false): UIStatePengarang = UIStatePengarang(
    detailPengarang = this.toDetailPengarang(),
    isEntryValid = isEntryValid
)
