package dev.amigo.telegramcachereader.domain.repository

import dev.amigo.telegramcachereader.domain.models.File

interface FileRepository {
    fun getFilesList(): List<File>
}