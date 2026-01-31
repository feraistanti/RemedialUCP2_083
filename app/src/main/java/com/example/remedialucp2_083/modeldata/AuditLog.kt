package com.example.remedialucp2_083.modeldata

data class AuditLog(
    val id: Long = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
    val statuspeminjaman: String = ""
)

data class DetailAuditLog(
    val id: Long = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
    val statuspeminjaman: String = ""
)

fun AuditLog.toDetailAuditLog(): DetailAuditLog = DetailAuditLog(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon,
    statuspeminjaman = statuspeminjaman
)

data class UIStateAuditLog(
    val detailAuditLog: DetailAuditLog = DetailAuditLog(),
    val isEntryValid: Boolean = false
)
fun AuditLog.toUiStateAuditLog(isEntryValid: Boolean = false): UIStateAuditLog = UIStateAuditLog(
    detailAuditLog = this.toDetailAuditLog(),
    isEntryValid = isEntryValid
)