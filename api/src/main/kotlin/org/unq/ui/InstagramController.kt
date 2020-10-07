package org.unq.ui

import io.javalin.http.*
import org.unq.ui.mappers.*
import org.unq.ui.token.TokenJWT
import org.unq.ui.model.DraftComment
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.Post
import java.time.format.DateTimeFormatter.*

data class ResultResponse(val result: String)
data class MessageResponse(val result: String, val message: String)

class InstagramController(val system: InstagramSystem) {

    val instagramAccessManager = InstagramAccessManager(system)


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
            ctx.status(200).json(PostMapper(idpost, post.description, post.portrait, post.landscape, likes, post.date.format(ISO_LOCAL_DATE), FollowersMapper(user.name, user.image), comments))

        } catch (e: NotFound){
            ctx.status(404).json(ResultResponse("Not found post with id $idpost"))
        }
    }


    /**
     * Agrega/elimina al usuario como que le dio like a ese post
     **/

    fun updateLikesById(ctx: Context) {

        val idPostToLike = ctx.pathParam("postId")
        val token = ctx.header("Authorization")
        val idUserLogged = instagramAccessManager.getUser(token!!).id

        try{
            system.updateLike(idPostToLike,idUserLogged)
            ctx.status(200).json(ResultResponse("Ok"))
        }catch (e: NotFound){
            ctx.status(404).json(ResultResponse("Not found Post with id $idPostToLike"))
        }

    }

    /**
     * Agrega un comentario al post con el id pasado como parametro
     */
    fun addCommentById(ctx: Context) {

        val body = ctx.bodyValidator<CommentMapper>()
                .check(
                        {
                            it.body != null
                        },
                        "Invalid body: comment should not be null"
                )
                .get().body


        val idPostToComment = ctx.pathParam("postId")
        val token = ctx.header("Authorization")
        val idUserLogged = instagramAccessManager.getUser(token!!).id


        var comment = DraftComment(body!!)

        try{
            system.addComment(idPostToComment,idUserLogged,comment)
            ctx.status(200).json(ResultResponse("Ok"))
        }catch (e: NotFound){
            ctx.status(404).json(ResultResponse("Not found user with id $idPostToComment"))
        }


    }

}