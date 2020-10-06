package org.unq.ui.mappers

import org.unq.ui.model.Comment
import org.unq.ui.model.User
import java.time.LocalDateTime

data class PostMapper (

    val idpost: String? = null,
    val description: String? = null,
    val portrait:String? = null,
    val landscape:String? = null,
    val likes: MutableList<User>,
    val date: LocalDateTime? = null,
    val user: User? = null,
    val comments: List<Comment>
)