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
    var search = ""

    fun searchTag(search : String) {
        val filteredPost = allPosts.filter { it.description.contains(search) }.toMutableList()
        updatePosts()
        if(search.isEmpty() || filteredPost.isEmpty() ){

            updatePosts()
            throw UserException("There are no results for your search")

        } else{
                allPosts = filteredPost
            }
    }

    private fun updatePosts() {
        postsUserLogged = instagramSystem.searchByUserId(user.id)
        allPosts = postsUserLogged.map { Postmodel(it.id, it.description, it.landscape, it.portrait) }.toMutableList()
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
            throw FieldsBlank()
        }else {
            if (!email.contains("@")) {
                throw InvalidUserOPassword()
            }
       }
        user = instagramSystem.login(email, password)
        setearDatos(user)
        updatePosts()
    }

    fun register(name: String, email: String, password: String, image: String){
        if(name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || image.isNullOrEmpty()){
            throw UserException("All fields must be completed")
        }
        if(!email.contains("@")){
            throw InvalidUserOPassword()
        }
        try{
            user = instagramSystem.register(name,email, password, image)
            setearDatos(user)
            updatePosts()
        } catch (e: UsedEmail){
            throw UserException("The email is already used")
        }
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
