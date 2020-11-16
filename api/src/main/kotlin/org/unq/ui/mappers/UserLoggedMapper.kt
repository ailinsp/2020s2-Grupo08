package org.unq.ui.mappers

data class UserLoggedMapper (
        val id: String? = null,
        val name :String? = null,
        val image :String? = null,
        val followers : MutableList<UserMapper>,
        val timeline : MutableList <PostTimelineMapper>
 )