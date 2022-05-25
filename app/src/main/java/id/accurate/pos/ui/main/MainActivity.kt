package id.accurate.pos.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.accurate.pos.databinding.ActivityMainBinding
import id.accurate.pos.viewmodel.ViewModelFactory
import id.accurate.pos.vo.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        val userAdapter = UserAdapter()

        viewModel.getUsers().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        Toast.makeText(this, "sedang loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        userAdapter.submitList(it.data)
                        for (data in it.data!!.iterator()){
                            Log.d("USERDATA", "user ${data.name}" )
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvUser){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }

    }
}