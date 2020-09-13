package org.unq.ui.model

import javafx.geometry.Pos
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable
import sun.font.TrueTypeFont

@Observable
class Postmodel(var id: String,  var description :String, var landscape: String, var portrait: String){ }

@Observable
class DatosUsuario(val id: String, var name: String, val email: String){}


@Observable
class DraftPostModel(postModel: Postmodel){
    var description = postModel.description
    var landscape = postModel.landscape
    var portrait = postModel.portrait
}
/*
    constructor(postModel: Postmodel) : this(){
        description = postModel.description
        landscape = postModel.landscape
        portrait = postModel.portrait
    }
  }
 */

@Observable
class InstagramModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {
    lateinit var posts : List<Postmodel>

    init {
        updatePosts()
    }

    private fun updatePosts() {
        posts = instagramSystem.posts.map { Postmodel(it.id,it.description,it.landscape, it.portrait) }
    }

    var user : User? = null
    var password = ""
    var name =""
    var email =  ""
    var id = ""
    var image = ""
    var emailUser = ""
    var passwordUser =""
    var selected : Postmodel? = null
    var search = ""
    var filteredPost : Post? = null
    var portrait= ""
    var landscape =""
    var description = ""
/*
    set(value) {
         check = true
         field = value
    }

    var check = false

 */
    fun login(email: String, password: String){
        emailUser = email
        passwordUser = password
        instagramSystem.login(email, password)
        print(emailUser)
    }

    fun addPost(id: String, post: DraftPostModel){
        instagramSystem.addPost(id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

   fun filterPost(search: String) {
       filteredPost = instagramSystem.posts.find { it.description == search } ?: throw NotFound("Post")
        print(filteredPost!!.description)
    }

    fun editPost(id:String, post: DraftPostModel){
        instagramSystem.editPost(id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

}