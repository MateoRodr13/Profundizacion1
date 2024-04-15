package com.um.profundizacion1.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.um.profundizacion1.R
import com.um.profundizacion1.controlador.Controlador
import com.um.profundizacion1.controlador.Resultado
import com.um.profundizacion1.modelo.Usuario

@Suppress("DEPRECATION")
class Panel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.panel)

        val allPreferences = Controlador.mostrarDatos(this)

        var name = allPreferences.getValue("name").toString() + " " + allPreferences.getValue("lastname").toString()
        val textViewNombreUsuario = findViewById<TextView>(R.id.textViewNombreUsuario)
        textViewNombreUsuario.text = name

        var id = allPreferences.getValue("id").toString()
        val textViewIdentificacionUsuario = findViewById<TextView>(R.id.textViewIdentificacionUsuario)
        textViewIdentificacionUsuario.text = id

        var city = allPreferences.getValue("city").toString()
        val textViewCiudadUsuario = findViewById<TextView>(R.id.textViewCiudadUsuario)
        textViewCiudadUsuario.text = city

        var email = allPreferences.getValue("email").toString()
        val textViewCorreoUsuario = findViewById<TextView>(R.id.textViewCorreoUsuario)
        textViewCorreoUsuario.text = email

        val btn_logout : Button = findViewById(R.id.btn_Logout)

        btn_logout.setOnClickListener {
            val accion = Intent(this, Inicio::class.java)
            startActivity(accion)
        }



    }
}