package org.unq.ui.mappers

data class UserPostbyIDMapper (
        val name:String? =null,
        val image:String? =null,
        val followers: MutableList<UserMapper>,
        val posts: MutableList<PostTimelineMapper>,
        val id: String? = null
)