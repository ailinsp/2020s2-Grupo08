package org.unq.ui.model

import org.unq.ui.bootstrap.getInstagramSystem
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException


@Observable
class Postmodel(var id: String,  var description :String, var landscape: String, var portrait: String){ }

@Observable
class UserDataModel(var name: String, var password: String, var image:String ){ }

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

    lateinit var allPosts: MutableList<Postmodel>
    lateinit var postsUserLogged : List<Post>
    lateinit var user: User
    var password = ""
    var name = ""
    var email = ""
    var id = ""
    var image = ""
    var selected: Postmodel? = null

    var search: String = ""
        set(tag) {
            field = tag
            search()
        }

    fun search() {
        instagramSystem.searchByTag(search)
        updatePostsHashtag(search)
    }

    private fun updatePosts() {
        postsUserLogged = instagramSystem.searchByUserId(user.id)
        allPosts = postsUserLogged.map { Postmodel(it.id, it.description, it.landscape, it.portrait) }.toMutableList()
    }

    private fun updatePostsHashtag(search: String){
        if(search.isNotEmpty()){
            allPosts = allPosts.filter { it.description.contains(search) }.toMutableList()
        } else{
            updatePosts()
        }
    }

    fun setearDatos(user: User) {
        name = user.name
        password = user.password
        email = user.email
        id = user.id
        image = user.image
    }

    fun login(email: String, password: String) {
            if(email.isNullOrEmpty() || password.isNullOrEmpty()){
                throw UserException("Ambos campos son requeridos")
            }
        user = instagramSystem.login(email, password)
        setearDatos(user)
        updatePosts()
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

    fun editProfile(user: UserDataModel) {
        var editedUser = instagramSystem.editProfile(id, user.name, user.password, user.image)
        setearDatos(editedUser)
    }
}
