package org.unq.ui.mappers

data class UserLoggedMapper (
        var name :String? = null,
        var image :String? = null,
        var followers : List<UserMapper>,
        var timeline : List <PostTimelineMapper>
 )