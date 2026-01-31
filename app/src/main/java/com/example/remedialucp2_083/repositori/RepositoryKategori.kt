package com.example.remedialucp2_083.repositori

interface RepositoryKategori {
    suspend fun getDataKategori(): List<Kategori>
    suspend fun postDataKategori(kategori: Kategori)

    suspend fun getSatuKategori(id: Long): Kategori?
    suspend fun editSatuKategori(id: Long, kategori: Kategori)
    suspend fun hapusSatuKategori(id: Long)
}

class FirebaseRepositoryKategori : RepositoryKategori {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("kategori")

    override suspend fun getDataKategori(): List<Kategori> {
        return try {
            collection.get().await().documents.map { doc ->
                Kategori(
                    id = doc.getLong("id") ?: 0L,
                    namaKategori = doc.getString("namaKategori") ?: "",
                    parentId = doc.getLong("parentId") ?: 0L,
                    status = doc.getString("status") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun postDataKategori(kategori: Kategori) {
        val docRef =
            if (kategori.id == 0L) collection.document()
            else collection.document(kategori.id.toString())

        val data = hashMapOf(
            "id" to (kategori.id.takeIf { it != 0L } ?: docRef.id.hashCode().toLong()),
            "namaKategori" to kategori.namaKategori,
            "parentId" to kategori.parentId,
            "status" to kategori.status
        )

        docRef.set(data).await()
    }

    override suspend fun getSatuKategori(id: Long): Kategori? {
        return try {
            val querySnapshot = collection.whereEqualTo("id", id).get().await()
            val document = querySnapshot.documents.firstOrNull()

            document?.let {
                Kategori(
                    id = it.getLong("id") ?: 0L,
                    namaKategori = it.getString("namaKategori") ?: "",
                    parentId = it.getLong("parentId") ?: 0L,
                    status = it.getString("status") ?: ""
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun editSatuKategori(id: Long, kategori: Kategori) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            collection.document(document.id).set(kategori).await()
        }
    }

    override suspend fun hapusSatuKategori(id: Long) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            document.reference.delete().await()
        }
    }
}