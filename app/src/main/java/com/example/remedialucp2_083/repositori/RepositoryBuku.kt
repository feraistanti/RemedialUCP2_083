package com.example.remedialucp2_083.repositori

import com.example.remedialucp2_083.modeldata.Buku
import com.google.firebase.firestore.FirebaseFirestore


interface RepositoryBuku {
    suspend fun getDataBuku(): List<Buku>
    suspend fun postDataBuku(buku: Buku)

    suspend fun getSatuBuku(id: Long): Buku?
    suspend fun editSatuBuku(id: Long, buku: Buku)
    suspend fun hapusSatuBuku(id: Long)
}

class FirebaseRepositoryBuku : RepositoryBuku {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("buku")

    override suspend fun getDataBuku(): List<Buku> {
        return try {
            collection.get().await().documents.map { doc ->
                Buku(
                    id = doc.getLong("id") ?: 0L,
                    judul = doc.getString("judul") ?: "",
                    penulis = doc.getString("penulis") ?: "",
                    statusPeminjaman = doc.getString("statusPeminjaman") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun postDataBuku(buku: Buku) {
        val docRef =
            if (buku.id == 0L) collection.document()
            else collection.document(buku.id.toString())

        val data = hashMapOf(
            "id" to (buku.id.takeIf { it != 0L } ?: docRef.id.hashCode().toLong()),
            "judul" to buku.judul,
            "penulis" to buku.penulis,
            "statusPeminjaman" to buku.statusPeminjaman
        )

        docRef.set(data).await()
    }

    override suspend fun getSatuBuku(id: Long): Buku? {
        return try {
            val querySnapshot = collection.whereEqualTo("id", id).get().await()
            val document = querySnapshot.documents.firstOrNull()

            document?.let {
                Buku(
                    id = it.getLong("id") ?: 0L,
                    judul = it.getString("judul") ?: "",
                    penulis = it.getString("penulis") ?: "",
                    statusPeminjaman = it.getString("statusPeminjaman") ?: ""
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun editSatuBuku(id: Long, buku: Buku) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            collection.document(document.id).set(buku).await()
        }
    }

    override suspend fun hapusSatuBuku(id: Long) {
        val querySnapshot = collection.whereEqualTo("id", id).get().await()
        for (document in querySnapshot.documents) {
            document.reference.delete().await()
        }
    }
}
