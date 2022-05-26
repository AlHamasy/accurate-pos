package id.accurate.pos.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.accurate.pos.R
import id.accurate.pos.databinding.ActivityMainBinding
import id.accurate.pos.ui.insert.InsertUserActivity
import id.accurate.pos.viewmodel.ViewModelFactory
import id.accurate.pos.vo.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        firstLoad()

        binding.fabInsert.setOnClickListener {
            startActivity(Intent(this, InsertUserActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_search_name -> searchByName("na")
            R.id.menu_sort_city -> sortByCity("Malang")
            R.id.menu_sort_name -> sortByName()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchByName(name : String){
        val mainAdapter = MainAdapter()
        viewModel.searchByName(name).observe(this) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun sortByCity(city : String){
        val mainAdapter = MainAdapter()
        viewModel.sortByCity(city).observe(this) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun sortByName(){
        val mainAdapter = MainAdapter()
        viewModel.sortByName().observe(this) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun firstLoad(){
        val mainAdapter = MainAdapter()
        viewModel.getUsers().observe(this) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showRecyclerView(mainAdapter: MainAdapter){
        with(binding.rvUser){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }


    override fun onRestart() {
        super.onRestart()
        firstLoad()
    }

}