package org.unq.ui.mappers

data class UserTimelineMapper (

        var name : String? = null,
        var image : String? = null,
        var follower : List<UserMapper>,
        var timeline : List<PostTimelineMapper>

)