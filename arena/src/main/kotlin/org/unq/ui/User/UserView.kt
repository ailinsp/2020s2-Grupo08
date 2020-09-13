package org.unq.ui.User

import org.unq.ui.model.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException


class UserView(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){

    override fun addActions(actionsPanel: Panel) {

        Button(actionsPanel) with {
            caption = "Add Post"
            width = 130
            onClick {
                val post = DraftPostModel()
                val view = EditPostView(this@UserView, post)
                view.onAccept {
                    modelObject.addPost(post)
                }
                view.open()

            }
        }

        Button(actionsPanel) with {
            caption = "Edit Post"
            width = 130
            onClick {
                val post = DraftPostModel(modelObject.selected!!)
                if (modelObject.selected == null){
                    throw UserException("No se puede editar el post")
                }
                val view = EditPostView(this@UserView, post)
                view.onAccept {
                    modelObject.editPost(modelObject.selected!!.id, post)
                }
                view.open()

            }
        }
        Button(actionsPanel) with {
            caption = "Delete Post"
            width = 130
            onClick {
                val deleteview = DeletePostView(this@UserView,modelObject.selected!!)
                    deleteview.onAccept{
                    modelObject.deletePost(modelObject.selected!!.id)
                }
                deleteview.open()
            }


            }
    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Publicaciones del usuario"

        Button(mainPanel) with {
            caption = "Edit Profile"
            width = 130
            onClick {
                thisWindow.close()
                UserEditProfile(owner, InstagramModel()).open()
            }
        }

        Label(mainPanel) withText "Search"

        TextBox(mainPanel) with {
                width = 150
                bindTo("search")
        }

        Button(mainPanel) with {
                caption = "Search"
                width = 130
                onClick {
                    modelObject.filterPost(modelObject.search)
                }


            }


        table<Postmodel>(mainPanel) {
            bindItemsTo("posts")
            bindSelectionTo("selected")
            visibleRows = 10


            column {
                title = "#"
                fixedSize = 30
                bindContentsTo("id")
            }
            column {
                title = "Descripcion"
                fixedSize = 130
                bindContentsTo("description")
            }

            column {
                title = "LandScape"
                fixedSize = 130
                bindContentsTo("landscape")
            }
            column {
                title = "Portrait"
                fixedSize = 230
                bindContentsTo("portrait")
            }
        }


/*
Panel(mainPanel) with {
    asColumns(3)

    Button(it) with {
        caption = "Add New Post"
        width = 130
        onClick {
            thisWindow.close()
            UserAddPostView(owner, DraftPostModel()).open()
        }
    }
    Button(it) with {
        caption = "Edit Post"
        width = 130
        onClick {
            thisWindow.close()
            UserAddPostView(owner, DraftPostModel()).open()
        }
    }
    Button(it) with {
        caption = "Delete Post"
        width = 130
        onClick {
            thisWindow.close()
            UserDeletePostView(owner, InstagramModel()).open()
        }
    }

}*/
}


















}