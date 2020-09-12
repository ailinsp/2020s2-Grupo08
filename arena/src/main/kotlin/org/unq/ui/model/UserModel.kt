package org.unq.ui.model

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    var user : User? = null
    var password = ""
    var posts = emptyList<Post>()
    var selected = ""


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