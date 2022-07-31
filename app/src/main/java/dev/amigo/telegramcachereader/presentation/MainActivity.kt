package dev.amigo.telegramcachereader.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.amigo.telegramcachereader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FileAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FileAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.fileList.observe(this, Observer {
            adapter.files = it
            updateUI()
        })
    }

    private fun updateUI() {
        if (adapter.itemCount > 0) {
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.noFilesTextView.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        setupPermissionsUi()
    }

    private fun setupPermissionsUi() {
        if(checkStoragePermission(this)){
            viewModel.getFilesList()
            binding.permissionView.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.noFilesTextView.visibility = View.GONE
            binding.permissionView.visibility = View.VISIBLE
            binding.permissionButton.setOnClickListener {
                requestStoragePermission(this)
            }
        }
    }
}