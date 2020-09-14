package org.unq.ui.User

import org.unq.ui.model.InstagramModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner


class UserEditProfile(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(actionsPanel: Panel) {


        Button(actionsPanel) with {
            caption = "Accept"
            width = 175
            onClick {
                val view = UserView(owner,this@UserEditProfile.modelObject)
                view.onAccept{}

                if(modelObject.name.isNullOrEmpty() || modelObject.password.isNullOrEmpty() || modelObject.image.isNullOrEmpty() ){
                    showError("Debe completar todos los campos")
                }
                else{
                    thisWindow.close()
                    UserView(owner, this@UserEditProfile.modelObject).open()
                }
            }

        }

        Button(actionsPanel) with {
            caption = "Cancel"
            width = 175
            onClick {
                thisWindow.close()
                cancel()
            }
        }










    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"
        setMinWidth(300)


            Label(mainPanel) withText "Nombre"
            TextBox(mainPanel) with {
                width = 150
                bindTo("name")
            }


            Label(mainPanel) withText "Password"
            TextBox(mainPanel) with {
                width = 150
                bindTo("password")
            }


            Label(mainPanel) withText "Image"
            TextBox(mainPanel) with {
                width = 150
                bindTo("image")
            }


        }
    }
