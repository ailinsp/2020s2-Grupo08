package org.unq.ui

import io.javalin.http.*
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.UsedEmail

data class OkResponse(val result: String = "Ok")
data class ErrorResponse(val message: String)

class InstagramController(val system: InstagramSystem) {

    /**
     * Registra a un usuario
     */
    fun register(ctx: Context) {
        val newUser = ctx.bodyValidator<UserRegisterMapper>()
                .check({
                    it.name != null && it.email != null && it.password != null && it.image != null },
                        "Invalid body: name, email, password and image should not be null")
                .get()

        try{
            system.register(newUser.name!!, newUser.email!!, newUser.password!!, newUser.image!!)
            ctx.status(201).json(OkResponse())
        } catch (e: UsedEmail){
            ctx.status(400).json(ErrorResponse(e.message!!))
        }
    }

    /**
     * Logea a un usuario
     */
    fun login(ctx: Context) {
    }

    /**
     * Retorna al usuario logueado con su timeline
     */
    fun getLoggedUser(ctx: Context) {
        ctx.result("Aca retorno el usuario logueado")
    }

    /**
     * Retorna al usuario con el mismo id que es pasado como parametro y sus post
     */
    fun getUserById(ctx: Context) {
        val userId = ctx.pathParam("userId")
        ctx.status(200).json(system.getUser(userId))
    }

    /**
     * Agrega/elimina al usuario como follower del userId
     */
    fun updateFollowerById(ctx: Context) { }

    /**
     * Retorna el post con id postId
     */
    fun getPostById(ctx: Context) {
        val postId = ctx.pathParam("postId")
        ctx.status(200).json(system.getPost(postId))
    }

    /**
     * Agrega/elimina al usuario como que le dio like a ese post
     */
    fun updateLikesById(ctx: Context) { }

    /**
     * Agrega un comentario al post con el id pasado como parametro
     */
    fun addCommentById(ctx: Context) { }

}