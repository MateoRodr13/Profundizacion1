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

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val editText_Email : EditText = findViewById(R.id.editText_Email)
        val editText_Password : EditText = findViewById(R.id.editText_Password)
        val btn_Login : Button = findViewById(R.id.btn_Login)
        val btn_register : Button = findViewById(R.id.btn_registrarInicio)


        /*btn_Login.setOnClickListener {
            var email = editText_Email.text.toString().trim()
            var password = editText_Password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@Inicio,
                    "El email y/o la contraseña es requerido",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                Toast.makeText(
                    this@Inicio,
                    "El email es: " + email + " y la contraseña es: " + password,
                    Toast.LENGTH_LONG
                ).show()

                val accion = Intent(this, Registro::class.java)
                startActivity(accion)
            }
        }*/

        btn_Login.setOnClickListener {
            val usuario : Usuario = Usuario(
                "",
                "",
                0,
                "",
                editText_Email.text.toString(),
                editText_Password.text.toString()
            )
            val resultado : Resultado = Controlador.ingresar(usuario, this)
            mostrarMensaje(resultado)

        }

        btn_register.setOnClickListener {
            val accion = Intent(this, Registro::class.java)
            startActivity(accion)
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