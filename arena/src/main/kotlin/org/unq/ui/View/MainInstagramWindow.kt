package org.unq.ui.View

import org.unq.ui.ViewModel.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException


class MainInstagramWindow(owner: WindowOwner, model: MainInstagramModel): SimpleWindow<MainInstagramModel>(owner, model){

    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel) with {
            caption = "Add Post"

            onClick {
                val post = DraftPostModel()
                val view = AddOrEditPostWindow(this@MainInstagramWindow, post)
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
                val view = AddOrEditPostWindow(this@MainInstagramWindow, post)
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
                val deleteview = DeletePostWindow(this@MainInstagramWindow,modelObject.selected!!)
                    deleteview.onAccept{
                    modelObject.deletePost(modelObject.selected!!.id)
                }
                deleteview.open()
            }
        }


        Button(actionsPanel) with {
            caption = "Logout"

            onClick {
                thisWindow.close()
                LoginWindow(owner, LoginModel(this@MainInstagramWindow.modelObject.managementModel)).open()
            }
        }



    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "User's Posts"
        setMinWidth(400)

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with { text ="Id : "}
            Label(it) with {  bindTo("id") }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with { text = "Email : "}
            Label(it) with { bindTo("email") }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with { text = "Name : "}
            Label(it) with { bindTo("name")}
        }

        Panel(mainPanel) with {

            Button(it) with {
                caption = "Edit Profile"
                var model = thisWindow.modelObject

                onClick {
                    val manager = this@MainInstagramWindow.modelObject.managementModel
                    val user = UserDataModel(manager.userLogged!!.name, manager.userLogged!!.password, manager.userLogged!!.image)
                    val view = EditProfileWindow(this@MainInstagramWindow, user)
                    view.onAccept {
                        model.editProfile(user)
                    }
                    view.open()
                }
            }
        }


        Label(mainPanel) with { text = ""}


        Panel(mainPanel) with {

            asHorizontal()

            Label(it) withText "Search a Post"

            TextBox(it) with {
                bindTo("search")
            }

            Button(it) with {
                var model = thisWindow.modelObject

                caption = "Search"
                onClick {
                    if(model.search.length > 1 && ! model.search.startsWith("#")){
                        throw UserException("The field must contain a Hastag(#) to look for it")
                    } else{
                        model.searchTag(model.search)
                    }
                }
            }
        }






        table<PostModel>(mainPanel) {
            bindItemsTo("allPosts")
            bindSelectionTo("selected")
            visibleRows = 16
            setWidth(1000)

            column {
                title = "#"
                fixedSize = 45
                bindContentsTo("id")
            }

            column {
                title = "Descripcion"
                fixedSize = 160
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