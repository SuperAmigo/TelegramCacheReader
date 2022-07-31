package dev.amigo.telegramcachereader.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.amigo.telegramcachereader.data.repository.TelegramFileRepositoryImpl
import dev.amigo.telegramcachereader.domain.models.File
import dev.amigo.telegramcachereader.domain.usecase.GetTelegramCacheFilesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private var fileListMutableLiveData = MutableLiveData<List<File>>()
    val fileList: LiveData<List<File>> = fileListMutableLiveData

    private val fileRepository = TelegramFileRepositoryImpl()
    private val getTelegramCacheFilesUseCase = GetTelegramCacheFilesUseCase(fileRepository)

    fun getFilesList() {
        viewModelScope.launch {
            getFilesListWithDefaultDispatcher()
        }
    }

    private suspend fun getFilesListWithDefaultDispatcher(){
        withContext(Dispatchers.Default) {
            fileListMutableLiveData.postValue(getTelegramCacheFilesUseCase.execute())
        }
    }

}
