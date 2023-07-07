package com.dcg.evc2_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.dcg.evc2_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("LOGGIN")
        binding.btnSign.setOnClickListener {

            val email: String = binding.tilEmail.editText?.text.toString()
            val password: String = binding.tilPassword.editText?.text.toString()
            if (loginValidate(email,password) && email =="ejemplo@idat.edu.pe" && password=="123456"){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Credenciales Validas", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
            }


        }
    }
    private fun loginValidate(email: String, password: String): Boolean {
        val validateEmail: Boolean = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val validatePassword: Boolean = password.length >= 6
        val condition: Boolean = validateEmail && validatePassword
        return condition
    }



}