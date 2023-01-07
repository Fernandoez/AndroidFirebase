package com.example.metodosfirebase.view.formlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metodosfirebase.R
import com.example.metodosfirebase.databinding.ActivityFormCadastroBinding
import com.example.metodosfirebase.databinding.ActivityFormLoginBinding
import com.example.metodosfirebase.view.formcadastro.FormCadastro
import com.example.metodosfirebase.view.telaprincipal.TelaPrincipal
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEntrar.setOnClickListener {view ->
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            else{
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { autenticacao ->
                    if(autenticacao.isSuccessful){
                        val intent = Intent(this, TelaPrincipal::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener {
                    val snackbar = Snackbar.make(view, "ERRO AO FAZER LOGIN", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        }

        binding.buttonCadastrar.setOnClickListener { view ->
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val usuarioAtual = auth.currentUser
        if(usuarioAtual != null){
            val intent = Intent(this, TelaPrincipal::class.java)
            startActivity(intent)
            finish()
        }
    }
}