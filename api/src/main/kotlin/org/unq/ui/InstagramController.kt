package org.unq.ui

import io.javalin.http.*
import org.unq.ui.mappers.UserLoginMapper
import org.unq.ui.mappers.UserMapper
import org.unq.ui.mappers.UserRegisterMapper
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.UsedEmail
import org.unq.ui.model.User

data class ResultResponse(val result: String)
data class MessageResponse(val result: String, val message: String)

class InstagramController(val system: InstagramSystem) {
    var loggedUser: User? = null

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
            ctx.status(201).json(ResultResponse("Ok"))
        } catch (e: UsedEmail){
            ctx.status(400).json(ResultResponse(e.message!!))
        }
    }

    /**
     * Logea a un usuario
     */
    fun login(ctx: Context) {
        val user = ctx.bodyValidator<UserLoginMapper>()
                .check({
                    it.email != null && it.password != null },
                        "Invalid body: email and password should not be null")
                .get()

        try{
            loggedUser = system.login(user.email!!, user.password!!)
            ctx.status(200).json(ResultResponse("Ok"))
        } catch (e: NotFound){
            ctx.status(404).json(MessageResponse("error","User not found"))
        }
    }

    /**
     * Retorna al usuario logueado con su timeline
     */
    fun getLoggedUser(ctx: Context) { }

    /**
     * Retorna al usuario con el mismo id que es pasado como parametro y sus posts
     */
    fun getUserById(ctx: Context) {
        val id = ctx.pathParam("userId")
        try {
            val user = system.getUser(id)
            val posts = system.searchByUserId(id)
            ctx.status(200).json(UserMapper(user.name, user.image, user.followers, posts))
        } catch (e: NotFound){
            ctx.status(404).json(ResultResponse("Not found user with id $id"))
        }
    }

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