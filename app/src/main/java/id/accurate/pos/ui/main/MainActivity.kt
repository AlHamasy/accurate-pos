package id.accurate.pos.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        val menuItem = menu?.findItem(R.id.menu_search_name)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.query_hint_search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String): Boolean {
                searchByName(p0)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_sort_city -> {
                showCities()
            }
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
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showToast(resources.getString(R.string.error_message))
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
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showToast(resources.getString(R.string.error_message))
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
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showToast(resources.getString(R.string.error_message))
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
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        mainAdapter.submitList(it.data)
                        showRecyclerView(mainAdapter)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showToast(resources.getString(R.string.error_message))
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

    private fun showLoading(show : Boolean){
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun showToast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        firstLoad()
    }

    private fun showCities(){
        val cities = ArrayList<String>()
        viewModel.getCities().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        it.data?.forEach { cityEntity ->
                            cities.add(cityEntity.name)
                        }
                        showListCity(cities)
                    }
                    Status.ERROR -> {
                        showToast(resources.getString(R.string.error_message))
                    }
                }
            }
        })
    }

    private fun showListCity(cities: ArrayList<String>) {

        val builderSingle: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builderSingle.setIcon(R.drawable.ic_baseline_location_city)
        builderSingle.setTitle(resources.getString(R.string.select_city))

        val arrayAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, cities)

        builderSingle.setNegativeButton(resources.getString(R.string.cancel), DialogInterface.OnClickListener {
                dialog, which -> dialog.dismiss()
        })

        builderSingle.setAdapter(arrayAdapter, DialogInterface.OnClickListener { dialog, which ->
                val city = arrayAdapter.getItem(which)
                sortByCity(city ?: "")
            })
        builderSingle.show()
    }

}