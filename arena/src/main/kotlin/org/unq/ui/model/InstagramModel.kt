package org.unq.ui.model

import org.unq.ui.bootstrap.getInstagramSystem
import org.uqbar.commons.model.annotations.Observable


@Observable
class Postmodel(var id: String,  var description :String, var landscape: String, var portrait: String){ }

@Observable
class DatosUsuario(var id: String,var email: String, var name:String ){ }



@Observable
class DraftPostModel(){
    var description = ""
    var landscape =""
    var portrait = ""

    constructor(postModel: Postmodel):this() {
        description = postModel.description
        landscape = postModel.landscape
        portrait = postModel.portrait

    }
}

@Observable
class InstagramModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    lateinit var posts: MutableList<Postmodel>

    init {
        updatePosts()
    }

    var search: String = ""
        set(tag) {
            field = tag
            search()
        }

    fun search() {
       // posts = posts.filter { it.description.contains(search) }.toMutableList()
        instagramSystem.searchByTag(search)
        updatePostsHashtag()
    }

    init {
        posts = instagramSystem.posts.map { val p = Postmodel(it.id,it.description,it.landscape,it.portrait)
            p }.toMutableList()
    }

    private fun updatePosts() {
        posts = instagramSystem.posts.map { Postmodel(it.id, it.description, it.landscape, it.portrait) }.toMutableList()
    }

    private fun updatePostsHashtag(){
        posts = posts.filter { it.description.contains(search) }.toMutableList()
    }

    lateinit var user: User
    var password = ""
    var name = ""
    var email = ""
    var id = ""
    var image = ""
    var selected: Postmodel? = null


    /*
    set(value) {
         check = true
         field = value
    }
    var check = false

 */
    fun setearDatos(user: User) {
        name = user.name
        email = user.email
        id = user.id
        image = user.image

    }

    fun login(email: String, password: String) {
        user = instagramSystem.login(email, password)
        setearDatos(user)
    }

    fun addPost(post: DraftPostModel) {
        instagramSystem.addPost(id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

    fun editPost(id: String, post: DraftPostModel) {
        instagramSystem.editPost(id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

    fun deletePost(noteid: String) {
        instagramSystem.deletePost(noteid)
        updatePosts()
    }

}
