package dev.amigo.telegramcachereader.domain.usecase

import dev.amigo.telegramcachereader.domain.models.File
import dev.amigo.telegramcachereader.domain.repository.FileRepository

class GetTelegramCacheFilesUseCase(private val fileRepository: FileRepository) {

    fun execute(): List<File> {
        return fileRepository.getFilesList()
    }

}