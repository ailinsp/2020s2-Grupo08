package org.unq.ui.mappers

data class UserPostbyIDMapper (
        val nombre:String? =null,
        val image:String? =null,
        val followers : MutableList<UserMapper>,
        val posts : MutableList<PostTimelineMapper>
)