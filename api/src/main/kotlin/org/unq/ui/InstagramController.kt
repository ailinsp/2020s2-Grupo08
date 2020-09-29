package org.unq.ui

import io.javalin.http.*
import org.unq.ui.model.InstagramSystem

class InstagramController(val system: InstagramSystem) {

    /**
     * Registra a un usuario
     */
    fun register(ctx: Context) { }

    /**
     * Logea a un usuario
     */
    fun login(ctx: Context) { }

    /**
     * Retorna al usuario logueado con su timeline
     */
    fun getLoggedUser(ctx: Context) {
        ctx.result("Aca retorno el usuario logueado")
    }

    /**
     * Retorna al usuario con el mismo id que es pasado como parametro y sus post
     */
    fun getUserById(ctx: Context) { }

    /**
     * Agrega/elimina al usuario como follower del userId
     */
    fun updateFollowerById(ctx: Context) { }

    /**
     * Retorna el post con id postId
     */
    fun getPostById(ctx: Context) { }

    /**
     * Agrega/elimina al usuario como que le dio like a ese post
     */
    fun updateLikesById(ctx: Context) { }

    /**
     * Agrega un comentario al post con el id pasado como parametro
     */
    fun addCommentById(ctx: Context) { }

}