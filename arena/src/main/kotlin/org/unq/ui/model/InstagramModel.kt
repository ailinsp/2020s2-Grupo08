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

// Si Busca algo que no tiene el # adelante tiro error de q falta el #
// Si busco algo que no esta muestro la tabla sin nada
    fun searchTag(search : String) {
        val filteredPost = allPosts.filter { it.description.contains(search) }.toMutableList()
        updatePosts()
        if(search.isEmpty() || filteredPost.isEmpty() ){

            updatePosts()
            throw UserException("No se encontraron resultados para su busqueda")

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
                throw UserException("Ambos campos son requeridos")
            }
        user = instagramSystem.login(email, password)
        setearDatos(user)
        updatePosts()
    }

    fun register(name: String, email: String, password: String, image: String){
        if(name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || image.isNullOrEmpty()){
            throw UserException("Todos los campos deben ser completados")
        }
        try{
            user = instagramSystem.register(name,email, password, image)
            setearDatos(user)
            updatePosts()
        } catch (e: UsedEmail){
            throw UserException("Ya existe una cuenta asociada al email provisto")
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
