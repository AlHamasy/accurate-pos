package id.accurate.pos.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.accurate.pos.R
import id.accurate.pos.databinding.ActivityInsertUserBinding

class InsertUserActivity : AppCompatActivity() {

    lateinit var binding : ActivityInsertUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertUserBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}