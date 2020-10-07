package org.unq.ui.mappers

data class UserTimelineMapper (
        val name : String? = null,
        val image : String? = null,
        val follower : List<UserMapper>,
        val timeline : List<PostTimelineMapper>

)