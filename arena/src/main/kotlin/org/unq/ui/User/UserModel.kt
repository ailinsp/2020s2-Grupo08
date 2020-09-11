package org.unq.ui.User

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    var post = emptyList<Post>()
    var selected = ""

    fun searchDescription(tag: String): List<Post> {
       return instagramSystem.searchByTag(tag)
    }

    fun getPostByUser(userId: String){
         post = instagramSystem.searchByUserId(userId)
    }



}