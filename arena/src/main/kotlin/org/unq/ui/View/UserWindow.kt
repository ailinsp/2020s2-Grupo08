package org.unq.ui.View

import org.unq.ui.ViewModel.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException


class UserWindow(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){

    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel) with {
            caption = "Add Post"

            onClick {
                val post = DraftPostModel()
                val view = AddOrEditPostWindow(this@UserWindow, post)
                view.onAccept {
                    modelObject.addPost(post)
                }
                view.open()
            }
        }

        Button(actionsPanel) with {
            caption = "Edit Post"

            onClick {
                if (modelObject.selected == null){
                    throw UserException("To edit a post, you must select one")
                }
                val post = DraftPostModel(modelObject.selected!!)
                val view = AddOrEditPostWindow(this@UserWindow, post)
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
                    throw UserException("To delete a post, you must select one")
                }
                val deleteview = DeletePostWindow(this@UserWindow,modelObject.selected!!)
                    deleteview.onAccept{
                    modelObject.deletePost(modelObject.selected!!.id)
                }
                deleteview.open()
            }
        }
    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "User's Posts"

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
                val user = DraftUserDataModel(UserDataModel(modelObject.name,modelObject.password,modelObject.image))
                val view = EditProfileWindow(this@UserWindow, user)
                view.onAccept {
                    modelObject.editProfile(user)
                }
                view.open()
            }
        }


        Label(mainPanel) withText "Search"
        TextBox(mainPanel) with {
            bindTo("search")
        }

        Button(mainPanel) with {
            caption = "Search"
            setWidth(300)
            onClick {
                if(modelObject.search.length > 1 && ! modelObject.search.startsWith("#")){
                    throw UserException("The field must contain a Hastag(#) to look for it")
                } else{
                    modelObject.searchTag(modelObject.search)
                }
            }
        }

        table<PostModel>(mainPanel) {
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