package org.unq.ui

import io.javalin.http.*
import org.unq.ui.mappers.CommentMapper
import org.unq.ui.mappers.FollowersMapper
import org.unq.ui.mappers.PostMapper
import org.unq.ui.token.TokenJWT
import org.unq.ui.mappers.UserMapper
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.Post

data class ResultResponse(val result: String)
data class MessageResponse(val result: String, val message: String)

class InstagramController(val system: InstagramSystem) {


    /**
     * Retorna al usuario con el mismo id que es pasado como parametro y sus posts
     */
    fun getUserById(ctx: Context) {
        val id = ctx.pathParam("userId")
        try {
            val token = ctx.header("Authorization")
            val user = system.getUser(id)
            val followers = user.followers.map{FollowersMapper(it.name, it.image)}.toMutableList()
            val posts = system.searchByUserId(id)
            ctx.header("Authorization", token!!)
            ctx.status(200).json(UserMapper(user.name, user.image, followers))
        } catch (e: NotFound){
            ctx.status(404).json(ResultResponse("Not found user with id $id"))
        }
    }


    /**
     * Agrega/elimina al usuario como follower del userId
     */
    fun updateFollowerById(ctx: Context) {


    }

    /**
     * Retorna el post con id postId
     */
    fun getPostById(ctx: Context) {


        val idpost = ctx.pathParam("postId")
        val post = system.getPost(idpost)
        val likes = post.likes.map{FollowersMapper(it.name, it.image)}.toMutableList()
        val user = post.user
        val comments = post.comments.map { CommentMapper(it.id, it.body, FollowersMapper(it.user.name, it.user.image)) }.toMutableList()

         try {
            val post = system.getPost(idpost)
            ctx.status(200).json(PostMapper(idpost, post.description, post.portrait, post.landscape, likes, post.date, FollowersMapper(user.name, user.image), comments))

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