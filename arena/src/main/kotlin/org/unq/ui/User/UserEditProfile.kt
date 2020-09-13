package org.unq.ui.User

import org.unq.ui.model.InstagramModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.lang.IllegalArgumentException

class UserEditProfile(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"
        setMinWidth(300)
        Panel(mainPanel) with {
            asColumns(4)
            Label(it) withText "Nombre"
            TextBox(it) with {
                width = 150
                bindTo("name")
            }

            asColumns(2)
            Label(it) withText "Password"
            TextBox(it) with {
                width = 150
                bindTo("password")
            }

            asColumns(2)
            Label(it) withText "Image"
            TextBox(it) with {
                width = 150
                bindTo("image")
            }
        }

        Panel(mainPanel) with {
            asColumns(2)
            Button(it) with {
                caption = "Accept"
                width = 175
                onClick {

                }

            }

            Button(it) with {
                caption = "Cancel"
                width = 175
                onClick { }
            }
        }
    }
}