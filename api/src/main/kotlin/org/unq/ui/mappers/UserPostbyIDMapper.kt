package org.unq.ui.mappers

data class UserPostbyIDMapper (
        var nombre:String? =null,
        var image:String? =null,
        var followers : List<UserMapper>,
        var post : List<PostTimelineMapper>
)