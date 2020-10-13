package org.unq.ui.ViewModel

import org.unq.ui.model.DraftPost
import org.unq.ui.model.Post
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

/**
 * Represents the user data values.
 */
@Observable
class UserDataModel(var name: String, var password: String, var image:String ){ }

/**
 * Represents the post data values.
 */
@Observable
class PostModel(var id: String, var description :String, var landscape: String, var portrait: String){ }

@Observable
class DraftPostModel(){
    var description = ""
    var landscape =""
    var portrait = ""

    constructor(postModel: PostModel):this() {
        description = postModel.description
        landscape = postModel.landscape
        portrait = postModel.portrait
    }
}

/**
 * Implements the domain model
 */
@Observable
class MainInstagramModel(var managementModel : ManagementModel) {

    var selected: PostModel? = null
    var search = ""
    var id = managementModel.userLogged!!.id
    var email = managementModel.userLogged!!.email
    var name = managementModel.userLogged!!.name


    /**
     * Edits the data of the logged in user.
     * @param user It's the model that contains new data of the user.
     */
    fun editProfile(user: UserDataModel) {
        managementModel.editProfile(user.name, user.password, user.image)
    }

    /**
     * Updates the displayed posts with the search input.
     * @param search It's the hashtag to search.
     * @throws UserException When there are no results that match the search.
     */
    fun searchTag(search : String) {
        managementModel.searchTag(search)
    }


    /**
     * Adds a post to the user logged in feed.
     * @param post It's the new post to add.
     */
    fun addPost(post: DraftPostModel) {
        managementModel.addPost(DraftPost(post.portrait, post.landscape, post.description))
        managementModel.updatePosts()
    }

    /**
     * Edits a post from the user logged in feed.
     * @param postId It's the post's id to modify.
     * @param post It's the model that contains the data of the new post.
     */
    fun editPost(postId: String, post: DraftPostModel) {
        managementModel.editPost(postId, DraftPost(post.portrait, post.landscape, post.description))
        managementModel.updatePosts()
    }

    /**
     * Deletes a post from the user logged in feed.
     * @param postId It's the post's id to delete.
     */
    fun deletePost(postId: String) {
        managementModel.dominio.deletePost(postId)
        managementModel.updatePosts()
    }


}
