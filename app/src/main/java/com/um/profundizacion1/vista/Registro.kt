package com.um.profundizacion1.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.um.profundizacion1.R
import com.um.profundizacion1.controlador.Controlador
import com.um.profundizacion1.controlador.Resultado
import com.um.profundizacion1.modelo.Usuario

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        val name_create : EditText = findViewById(R.id.name_create)
        val lastname_create : EditText = findViewById(R.id.lastname_create)
        val identifier_create : EditText = findViewById(R.id.identifier_create)
        val cityCreate : EditText = findViewById(R.id.city_create)
        val email_create : EditText = findViewById(R.id.email_create)
        val password_create : EditText = findViewById(R.id.password_create)
        val irRegistro: Button = findViewById(R.id.btn_registrar)

        irRegistro.setOnClickListener {
            var name = name_create.text.toString().trim()
            var lastname = lastname_create.text.toString().trim()
            var identifier = identifier_create.text.toString().trim()
            var city = cityCreate.text.toString().trim()
            var email = email_create.text.toString().trim()
            var password = password_create.text.toString().trim()

            if (identifier.isNotEmpty()){
                var identifierInt = identifier.toInt()
                val usuario : Usuario = Usuario(name, lastname, identifierInt, city, email, password)
                val resultado : Resultado = Controlador.registrar(usuario, this)
                mostrarMensaje(resultado)
            }else{
                mostrarMensaje(Resultado.REGISTRO_ERROR)
            }

        }

    }

    private fun mostrarMensaje(resultado: Resultado){
        when(resultado){
            Resultado.VALIDACION_ERROR -> Toast.makeText(this, "Error en la validación.", Toast.LENGTH_SHORT).show()
            Resultado.REGISTRO_EXITOSO -> Toast.makeText(this, "Usuario registrado existosamente.", Toast.LENGTH_SHORT).show()
            Resultado.REGISTRO_ERROR -> Toast.makeText(this, "Error al registrar usuario.", Toast.LENGTH_SHORT).show()
            Resultado.USUARIO_EXISTENTE -> Toast.makeText(this, "Usuario ya existe.", Toast.LENGTH_SHORT).show()
            Resultado.USUARIO_NO_EXISTENTE -> Toast.makeText(this, "Usuario no existe.", Toast.LENGTH_SHORT).show()
            Resultado.INGRESO_EXITOSO -> Toast.makeText(this, "Inicio sesion", Toast.LENGTH_SHORT).show()
        }
    }
}