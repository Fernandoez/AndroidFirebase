package com.example.metodosfirebase.view.telaprincipal

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.metodosfirebase.R
import com.example.metodosfirebase.databinding.ActivityFormCadastroBinding
import com.example.metodosfirebase.databinding.ActivityTelaPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val db = FirebaseFirestore.getInstance()
    private val usuarioAtual = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUsuario.setOnClickListener {
            db.collection("Cliente").document("12345678911").addSnapshotListener { document, error ->
                if(document != null){
                    binding.dadosUsuario.text = document.getString("Nome")
                }
            }
        }

        binding.buttonCupom.setOnClickListener {
            db.collection("cupons").document("O que seria").addSnapshotListener { document, error ->
                if(document != null){
                    binding.dadosCupom.text = document.getString("codigo")
                }
            }
        }
    }
}