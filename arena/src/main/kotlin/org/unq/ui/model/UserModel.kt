package org.unq.ui.model

import javafx.geometry.Pos
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable
import sun.font.TrueTypeFont

@Observable
class Postmodel(var id: String,  var description :String, var landscape: String, var portrait: String){

}

@Observable
class DraftPostModel(){
    var description = ""
    var landscape = ""
    var portrait = ""


    constructor(postModel: Postmodel) : this(){
        description = postModel.description
        landscape = postModel.landscape
        portrait = postModel.portrait
    }
}


@Observable
class InstagramModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    var user : User? = null
    var password = ""
    var name = ""
    var email =  ""
    var id = ""
    var image = ""
    var emailUser = ""
    var selected : Postmodel? = null
    var search = ""
    var filteredPost : Post? = null
    var portrait= ""
    var landscape =""
    var description = ""


    set(value) {
        check = true
        field = value
    }

    var check = false


    lateinit var posts : List<Postmodel>

    init {
        updatePosts()
    }

    private fun updatePosts() {
        posts = instagramSystem.posts.map { Postmodel(it.id,it.description,it.landscape, it.portrait) }
    }


    fun login(userEmail: String, password: String){
        instagramSystem.login(userEmail, password)
    }



   fun filterPost(search: String) {
       filteredPost = instagramSystem.posts.find { it.description == search } ?: throw NotFound("Post")
        print(filteredPost!!.description)
    }

















    fun searchDescription(tag: String): List<Post> {
       return instagramSystem.searchByTag(tag)
    }

    /* fun getPostByUser(userId: String){
        posts = instagramSystem.searchByUserId(userId)
    }*/



}