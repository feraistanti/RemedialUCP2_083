package com.example.remedialucp2_083.repositori


import android.app.Application

interface ContainerApp {
    val repositoryBuku: RepositoryBuku
    val repositoryKategori: RepositoryKategori
    val repositoryPengarang: RepositoryPengarang
    val repositoryAuditLog: RepositoryAuditLog
}

class DefaultContainerApp : ContainerApp {

    override val repositoryBuku: RepositoryBuku by lazy {
        FirebaseRepositoryBuku()
    }

    override val repositoryKategori: RepositoryKategori by lazy {
        FirebaseRepositoryKategori()
    }

    override val repositoryPengarang: RepositoryPengarang by lazy {
        FirebaseRepositoryPengarang()
    }

    override val repositoryAuditLog: RepositoryAuditLog by lazy {
        FirebaseRepositoryAuditLog()
    }
}

class AplikasiDataPerpustakaan : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainerApp()
    }
}
