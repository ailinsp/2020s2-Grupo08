package org.unq.ui.mappers

import org.unq.ui.model.Post

data class UserMapper(
    val name: String? = null,
    val image: String? = null,
    val followers: MutableList<FollowersMapper>,
    //val timeline: List<Post>
)