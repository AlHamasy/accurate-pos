package id.accurate.pos.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.accurate.pos.R
import id.accurate.pos.data.remote.request.RequestUser
import id.accurate.pos.databinding.ActivityInsertUserBinding
import id.accurate.pos.viewmodel.ViewModelFactory
import id.accurate.pos.vo.Status

class InsertUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInsertUserBinding
    private lateinit var viewModel : InsertUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.add_user_title)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[InsertUserViewModel::class.java]

        getCitiesForSpinner()

        binding.btnAddUser.setOnClickListener {
            validation()
        }
    }

    private fun validation(){

        val name = binding.edtName.text.toString()
        val address = binding.edtAddress.text.toString()
        val email = binding.edtEmail.text.toString()
        val phone = binding.edtPhone.text.toString()
        val city = binding.spinCity.selectedItem.toString()

        if (TextUtils.isEmpty(name)){
            binding.edtName.error = resources.getString(R.string.empty_name)
        }
        else if (TextUtils.isEmpty(address)){
            binding.edtAddress.error = resources.getString(R.string.empty_address)
        }
        else if (TextUtils.isEmpty(email)){
            binding.edtEmail.error = resources.getString(R.string.empty_email)
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.edtEmail.error = resources.getString(R.string.invalid_email)
        }
        else if (TextUtils.isEmpty(phone)){
            binding.edtPhone.error = resources.getString(R.string.empty_phone)
        }
        else{
            submit(name, address, email, phone, city)
        }
    }

    private fun submit(name: String, address: String, email: String, phone: String, city: String) {

        val requestUser = RequestUser(address, phone, city, name, email)
        viewModel.insertUser(requestUser).observe(this, Observer {
            showToast(resources.getString(R.string.add_success))
            finish()
        })
    }

    private fun getCitiesForSpinner(){
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
                        configureSpinnerCities(cities)
                    }
                    Status.ERROR -> {
                        showToast(resources.getString(R.string.error_message))
                    }
                }
            }
        })
    }

    private fun configureSpinnerCities(cities: ArrayList<String>) {

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)
        binding.spinCity.adapter = arrayAdapter
    }

    private fun showToast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}