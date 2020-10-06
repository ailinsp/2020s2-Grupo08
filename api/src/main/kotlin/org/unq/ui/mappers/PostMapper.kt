package org.unq.ui.mappers

import org.unq.ui.model.Comment
import org.unq.ui.model.User
import java.time.LocalDateTime

data class PostMapper (

    val idpost: String? = null,
    val description: String? = null,
    val portrait:String? = null,
    val landscape:String? = null,
    val likes: MutableList<FollowersMapper>,
    val date: LocalDateTime? = null,
    val user: FollowersMapper? = null,
    val comments: MutableList<CommentMapper>
)