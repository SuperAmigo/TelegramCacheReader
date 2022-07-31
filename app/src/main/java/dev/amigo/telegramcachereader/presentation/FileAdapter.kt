package dev.amigo.telegramcachereader.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.amigo.telegramcachereader.databinding.ItemFileBinding
import dev.amigo.telegramcachereader.domain.models.File

class FileAdapter : RecyclerView.Adapter<FileAdapter.FilesViewHolder>() {

    var files: List<File> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFileBinding.inflate(inflater, parent, false)
        return FilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
        val fileListItem = files[position]
        with(holder.binding) {
            fileNameTextView.text = fileListItem.name
            fileSizeTextView.text = fileListItem.size.toString()
        }
    }

    override fun getItemCount() = files.size

    class FilesViewHolder(
        val binding: ItemFileBinding
    ) : RecyclerView.ViewHolder(binding.root)
}