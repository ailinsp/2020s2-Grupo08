package org.unq.ui.model

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable

@Observable
class Postmodel(var id: String,  var description :String, var landscape: String, var portrait: String)


@Observable
class UserModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    var post = instagramSystem.posts.map { Postmodel(it.id,it.description,it.landscape, it.portrait) }
    var selected : Postmodel? = null


    var user : User? = null
    var password = ""
    var email =  ""
    var posts = emptyList<Post>()



    fun login(userEmail: String, password: String): User{

        user = instagramSystem.login(userEmail, password)
        posts = instagramSystem.searchByUserId(this.user!!.id)

        return user as User
    }














    fun searchDescription(tag: String): List<Post> {
       return instagramSystem.searchByTag(tag)
    }

    /* fun getPostByUser(userId: String){
        posts = instagramSystem.searchByUserId(userId)
    }*/



}