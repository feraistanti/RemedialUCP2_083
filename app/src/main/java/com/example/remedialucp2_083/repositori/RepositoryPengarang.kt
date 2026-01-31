package com.example.remedialucp2_083.repositori

interface RepositoryPengarang {
    suspend fun getDataPengarang(): List<Pengarang>
    suspend fun postDataPengarang(pengarang: Pengarang)

    suspend fun getSatuPengarang(id: Long): Pengarang?
    suspend fun editSatuPengarang(id: Long, pengarang: Pengarang)
    suspend fun hapusSatuPengarang(id: Long)
}

class FirebaseRepositoryPengarang : RepositoryPengarang {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("pengarang")

    override suspend fun getDataPengarang(): List<Pengarang> {
        return try {
            collection.get().await().documents.map { doc ->
                Pengarang(
                    id = doc.getLong("id")?.toLong() ?: 0L,
                    namaPengarang = doc.getString("namaPengarang") ?: "",
                    asal = doc.getString("asal") ?: "",
                    status = doc.getString("status") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun postDataPengarang(pengarang: Pengarang) {
        val docRef =
            if (pengarang.id == 0L) collection.document()
            else collection.document(pengarang.id.toString())

        val data = hashMapOf(
            "id" to (pengarang.id.takeIf { it != 0L } ?: docRef.id.hashCode().toLong()),
            "namaPengarang" to pengarang.namaPengarang,
            "asal" to pengarang.asal,
            "status" to pengarang.status
        )

        docRef.set(data).await()
    }

    override suspend fun getSatuPengarang(id: Long): Pengarang? {
        return try {
            val querySnapshot = collection.whereEqualTo("id", id).get().await()
            val document = querySnapshot.documents.firstOrNull()

            document?.let {
                Pengarang(
                    id = it.getLong("id") ?: 0L,
                    namaPengarang = it.getString("namaPengarang") ?: "",
                    asal = it.getString("asal") ?: "",
                    status = it.getString("status") ?: ""
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun editSatuPengarang(id: Long, pengarang: Pengarang) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            collection.document(document.id).set(pengarang).await()
        }
    }

    override suspend fun hapusSatuPengarang(id: Long) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            document.reference.delete().await()
        }
    }
}