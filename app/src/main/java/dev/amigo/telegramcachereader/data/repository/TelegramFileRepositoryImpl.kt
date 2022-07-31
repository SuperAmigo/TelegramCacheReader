package dev.amigo.telegramcachereader.data.repository

import android.os.Environment
import dev.amigo.telegramcachereader.domain.models.File
import dev.amigo.telegramcachereader.domain.repository.FileRepository
import java.text.DecimalFormat

class TelegramFileRepositoryImpl : FileRepository {

    private val telegramCacheFolder = "/Android/data/org.telegram.messenger/cache";

    override fun getFilesList(): List<File> {
        var files = listOf<File>()
        val path = Environment.getExternalStorageDirectory().toString() + telegramCacheFolder
        val dir = java.io.File(path)
        if (dir.exists()) {
            val filesCache = dir.listFiles()?.filter { it.isFile }
            if (!filesCache.isNullOrEmpty()) {
                files = (filesCache.indices).map {
                    val sizeKb = filesCache[it].length() / 1024.0
                    val size = DecimalFormat("0.00").format(sizeKb) + " kB"
                    File(name = filesCache[it].name, size = size)
                }
            }
        }
        return files
    }

}