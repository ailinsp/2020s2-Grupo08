package org.unq.ui.ViewModel

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.*
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
class InstagramModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {
    lateinit var allPosts: MutableList<PostModel>
    lateinit var postsUserLogged : List<Post>
    lateinit var user: User
    var password = ""
    var name = ""
    var email = ""
    var id = ""
    var image = ""
    var selected: PostModel? = null
    var search = ""

    /**
     * Updates the displayed posts with the search input.
     * @param search It's the hashtag to search.
     * @throws UserException When there are no results that match the search.
     */
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

    /**
     * Updates the logged user posts.
     */
    private fun updatePosts() {
        postsUserLogged = instagramSystem.searchByUserId(user.id)
        allPosts = postsUserLogged.map{ PostModel(it.id, it.description, it.landscape, it.portrait) }.toMutableList()
    }

    /**
     * Sets up the data of the logged in user.
     * @param user It's the logged in user.
     */
    fun setData(user: User) {
        name = user.name
        password = user.password
        email = user.email
        id = user.id
        image = user.image
    }

    /**
     * Logs in a user into the system.
     * @param email It's the user email to log in.
     * @param password It's the user password to log in.
     * @throws FieldsBlank When the inputs are incomplete.
     * @throws InvalidUserOPassword When the email input is invalid.
     */
    fun login(email: String, password: String) {
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            throw FieldsBlank()
        }else {
            if (!email.contains("@")) {
                throw InvalidUserOPassword()
            }
       }
        user = instagramSystem.login(email, password)
        setData(user)
        updatePosts()
    }

    /**
     * Register and logs in a user into the system.
     * @param name It's the user name to register.
     * @param email It's the user email to register.
     * @param password It's the user password to register.
     * @param image It's the user image to register.
     */
    fun register(name: String, email: String, password: String, image: String){
        if(name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || image.isNullOrEmpty()){
            throw UserException("All fields must be completed")
        }
        if(!email.contains("@")){
            throw InvalidUserOPassword()
        }
        try{
            user = instagramSystem.register(name,email, password, image)
            setData(user)
            updatePosts()
        } catch (e: UsedEmail){
            throw UserException("The email is already used")
        }
    }

    /**
     * Adds a post to the user logged in feed.
     * @param post It's the new post to add.
     */
    fun addPost(post: DraftPostModel) {
        instagramSystem.addPost(id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

    /**
     * Edits a post from the user logged in feed.
     * @param postId It's the post's id to modify.
     * @param post It's the model that contains the data of the new post.
     */
    fun editPost(postId: String, post: DraftPostModel) {
        instagramSystem.editPost(postId, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

    /**
     * Deletes a post from the user logged in feed.
     * @param postId It's the post's id to delete.
     */
    fun deletePost(postId: String) {
        instagramSystem.deletePost(postId)
        updatePosts()
    }

    /**
     * Edits the data of the logged in user.
     * @param user It's the model that contains new data of the user.
     */
    fun editProfile(user: UserDataModel) {
        val editedUser = instagramSystem.editProfile(id, user.name, user.password, user.image)
        setData(editedUser)
    }

    fun cleanUserAttributes() {
        id = ""
        name = ""
        image = ""
        allPosts = emptyList<PostModel>().toMutableList()
        password = ""
        email = "";
    }
}
