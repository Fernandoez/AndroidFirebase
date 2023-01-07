package com.example.metodosfirebase.view.formdados

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.metodosfirebase.databinding.ActivityFormDadosBinding
import com.example.metodosfirebase.view.telaprincipal.TelaPrincipal
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class FormDados : AppCompatActivity() {

    private  lateinit var binding: ActivityFormDadosBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDados.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val cpf = binding.editCpf.text.toString()

            if(nome.isEmpty() || cpf.isEmpty()){
                val snackbar = Snackbar.make(it, "NOME OU CPF VAZIO", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            else{

                val usuariosMap = hashMapOf(
                    "Nome" to nome,
                    "Cpf" to cpf
                )

                db.collection("Cliente").document(cpf).set(usuariosMap).addOnCompleteListener {
                    Log.d("db", "Sucesso")
                    val intent = Intent(this, TelaPrincipal::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    println("Erro")
                }
            }
        }
    }
}