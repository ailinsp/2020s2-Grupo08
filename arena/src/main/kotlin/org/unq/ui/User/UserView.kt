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
            //width = 130
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
           // width = 130
            onClick {
                if (modelObject.selected == null){
                    throw UserException("Se debe seleccionar un Post")
                }
                val post = DraftPostModel(modelObject.selected!!)
                val view = EditPostView(this@UserView, post)
                view.onAccept {
                    modelObject.editPost(modelObject.selected!!.id, post)
                }
                view.open()
            }
        }

        Button(actionsPanel) with {
            caption = "Delete Post"

            onClick {
                if (modelObject.selected == null) {
                    throw UserException("Se debe seleccionar un Post")
                }
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


        Label(mainPanel) with {
            text = "Id : ${modelObject.id} "
            alignLeft()
        }
        Label(mainPanel) with {
            text = "Email : ${modelObject.email} "
            alignLeft()
        }
        Label(mainPanel) with {
            text = "Name : ${modelObject.name} "
            alignLeft()
        }

        Button(mainPanel) with {
            caption = "Edit Profile"
            setWidth(300)
            onClick {
                val user = UserDataModel(modelObject.name, modelObject.password, modelObject.image)
                val view = UserEditProfile(this@UserView, user)
                view.onAccept {
                    modelObject.editProfile(user)

                }

                view.open()
            }
        }

        GroupPanel(mainPanel) with {
            title = "Busqueda por Descripción"
            asHorizontal()
            TextBox(it) with {
                bindTo("search")
            }
        }

        table<Postmodel>(mainPanel) {
            bindItemsTo("allPosts")
            bindSelectionTo("selected")
            visibleRows = 10

            column {
                title = "#"
                fixedSize = 45
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
    }
    
}