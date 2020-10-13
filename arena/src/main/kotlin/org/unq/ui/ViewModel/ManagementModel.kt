package org.unq.ui.ViewModel

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.DraftPost
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException


@Observable
class ManagementModel(var dominio: InstagramSystem = getInstagramSystem()){

    var userLogged :User? = null;
    lateinit var allPosts: MutableList<PostModel>
    lateinit var postsUserLogged : List<Post>


    fun login(email: String, password: String){
        userLogged = dominio.login(email, password)
    }

    fun editProfile(name: String, password: String, image: String) {
        dominio.editProfile(userLogged!!.id, name, password, image)
    }

    fun addPost(draftPost: DraftPost) {
        dominio.addPost(userLogged!!.id, draftPost)
    }

    fun searchByUserId(id: String): List<Post> {
       return dominio.searchByUserId(id)
    }

    fun editPost(postId: String, draftPost: DraftPost) {
        dominio.editPost(postId, draftPost)
    }

    fun updatePosts() {
        postsUserLogged = dominio.searchByUserId(userLogged!!.id)
        allPosts = postsUserLogged.map{ PostModel(it.id, it.description, it.landscape, it.portrait) }.toMutableList()
    }

    fun searchTag(search: String) {
        val filteredPost = allPosts.filter { it.description.contains(search) }.toMutableList()
        updatePosts()
        if(filteredPost.isEmpty() ){
            updatePosts()
            throw UserException("There are no results for your search")
        }
        if(search.isEmpty()) {
            updatePosts()
        } else{
            allPosts = filteredPost
        }
    }

}