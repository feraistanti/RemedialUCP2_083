package com.example.remedialucp2_083.repositori


interface RepositoryAuditLog {
    suspend fun getDataAuditLog(): List<AuditLog>
    suspend fun postDataAuditLog(auditLog: AuditLog)

    suspend fun getSatuAuditLog(id: Long): AuditLog?
    suspend fun editSatuAuditLog(id: Long, auditLog: AuditLog)
    suspend fun hapusSatuAuditLog(id: Long)
}

class FirebaseRepositoryAuditLog : RepositoryAuditLog {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("auditLog")

    override suspend fun getDataAuditLog(): List<AuditLog> {
        return try {
            collection.get().await().documents.map { doc ->
                AuditLog(
                    id = doc.getLong("id") ?: 0L,
                    aksi = doc.getString("aksi") ?: "",
                    dataSebelum = doc.getString("dataSebelum") ?: "",
                    dataSesudah = doc.getString("dataSesudah") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun postDataAuditLog(auditLog: AuditLog) {
        val docRef =
            if (auditLog.id == 0L) collection.document()
            else collection.document(auditLog.id.toString())

        val data = hashMapOf(
            "id" to (auditLog.id.takeIf { it != 0L } ?: docRef.id.hashCode().toLong()),
            "aksi" to auditLog.aksi,
            "dataSebelum" to auditLog.dataSebelum,
            "dataSesudah" to auditLog.dataSesudah
        )

        docRef.set(data).await()
    }

    override suspend fun getSatuAuditLog(id: Long): AuditLog? {
        return try {
            val querySnapshot = collection.whereEqualTo("id", id).get().await()
            val document = querySnapshot.documents.firstOrNull()

            document?.let {
                AuditLog(
                    id = it.getLong("id") ?: 0L,
                    aksi = it.getString("aksi") ?: "",
                    dataSebelum = it.getString("dataSebelum") ?: "",
                    dataSesudah = it.getString("dataSesudah") ?: ""
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun editSatuAuditLog(id: Long, auditLog: AuditLog) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            collection.document(document.id).set(auditLog).await()
        }
    }

    override suspend fun hapusSatuAuditLog(id: Long) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            document.reference.delete().await()
        }
    }
}