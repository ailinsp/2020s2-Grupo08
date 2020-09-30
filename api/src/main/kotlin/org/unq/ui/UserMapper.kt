package org.unq.ui

import org.unq.ui.model.Post
import org.unq.ui.model.User

data class UserMapper(
        val name: String? = null,
        val image: String? = null,
        val followers: MutableList<User>,
        val posts: List<Post>
)