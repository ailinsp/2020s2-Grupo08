package org.unq.ui.mappers

import org.unq.ui.model.Comment
import org.unq.ui.model.Post
import org.unq.ui.model.User

data class PostMapper(
    val idpost: String? = null ,
    val description: String? = null,
    val portrait:String? = null,
    val landscape:String? = null,
    val likes: MutableList<User>,
    val comments: List<Comment> )

