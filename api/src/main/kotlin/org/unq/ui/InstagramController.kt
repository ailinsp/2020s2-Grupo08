package org.unq.ui

import io.javalin.http.*
import org.unq.ui.mappers.PostMapper
import org.unq.ui.token.TokenJWT
import org.unq.ui.mappers.UserMapper
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound

data class ResultResponse(val result: String)
data class MessageResponse(val result: String, val message: String)

class InstagramController(val system: InstagramSystem) {

    val tokenController = TokenJWT()

    /**
     * Retorna al usuario con el mismo id que es pasado como parametro y sus posts
     */
    fun getUserById(ctx: Context) {
        val id = ctx.pathParam("userId")
        try {
            val token = ctx.header("Authorization")
            val user = system.getUser(id)
            val posts = system.searchByUserId(id)
            ctx.header("Authorization", token!!)
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
    fun getPostById(ctx: Context) {
        val idpost = ctx.pathParam("postId")
        try {
            val post = system.getPost(idpost)
            ctx.status(200).json(PostMapper(idpost, post.description, post.portrait, post.landscape, post.likes, post.comments))
        } catch (e: NotFound){
            ctx.status(404).json(ResultResponse("Not found post with id $idpost"))
        }
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