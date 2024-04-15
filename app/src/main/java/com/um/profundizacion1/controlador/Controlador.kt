@file:Suppress("DEPRECATION")

package com.um.profundizacion1.controlador

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.um.profundizacion1.R
import com.um.profundizacion1.modelo.Usuario
import com.um.profundizacion1.vista.Inicio
import com.um.profundizacion1.vista.Panel

enum class Resultado{
    VALIDACION_ERROR,
    REGISTRO_EXITOSO,
    REGISTRO_ERROR,
    USUARIO_EXISTENTE,
    USUARIO_NO_EXISTENTE,
    INGRESO_EXITOSO
}

object Controlador{

    private val usuariosRegistrados: MutableList<Usuario> = mutableListOf()

    fun ingresar(usuario: Usuario, context: Context): Resultado {
        /*if (usuario.email.isEmpty()){
            return Resultado.VALIDACION_ERROR
        }
        if (usuario.password.isEmpty()){
            return Resultado.VALIDACION_ERROR
        }

        for (usuarioAuxiliar in usuariosRegistrados){
            if (usuarioAuxiliar.email == usuario.email && usuarioAuxiliar.password == usuario.password){
                val intent = Intent(context, Panel::class.java)
                context.startActivity(intent)
                return Resultado.INGRESO_EXITOSO
            }
        }*/

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        // Obtener el correo electrónico y la contraseña guardados
        val email = preferences.getString("email", "")
        val password = preferences.getString("password", "")

        // Verificar si el correo electrónico y la contraseña son válidos
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            return Resultado.USUARIO_NO_EXISTENTE
        }

        if (usuario.email == email && usuario.password == password){
            val intent = Intent(context, Panel::class.java)
            context.startActivity(intent)
            return Resultado.INGRESO_EXITOSO
        }

        return Resultado.USUARIO_NO_EXISTENTE
    }

    fun registrar(usuario: Usuario, context: Context): Resultado {
        if (usuario.name.isEmpty() || usuario.lastname.isEmpty() || usuario.id.toString().isEmpty() || usuario.email.isEmpty() || usuario.password.isEmpty()){
            return Resultado.VALIDACION_ERROR
        }

        /*for (usuarioAuxiliar in usuariosRegistrados){
            if (usuarioAuxiliar.email == usuario.email){
                return Resultado.USUARIO_EXISTENTE
            }
        }*/

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val email = preferences.getString("email", "")

        // Verificar si el correo electrónico y la contraseña son válidos
        if (usuario.email == email) {
            return Resultado.USUARIO_EXISTENTE
        }

        val editor = preferences.edit()

        editor.putString("name", usuario.name)
        editor.putString("lastname", usuario.lastname)
        editor.putInt("id", usuario.id)
        editor.putString("city", usuario.city)
        editor.putString("email", usuario.email)
        editor.putString("password", usuario.password)

        // Aplicar los cambios
        editor.apply()

        //usuariosRegistrados.add(usuario)

        val intent = Intent(context, Inicio::class.java)
        context.startActivity(intent)

        return Resultado.REGISTRO_EXITOSO
    }

    fun mostrarDatos(context: Context): Map<String, *> {

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        return preferences.all
    }

}