package org.unq.ui.mappers

data class UserLoggedMapper (

        val name :String? = null,
        val image :String? = null,
        val followers : MutableList<UserMapper>,
        val timeline : MutableList <PostTimelineMapper>,
        val id: String? = null
)