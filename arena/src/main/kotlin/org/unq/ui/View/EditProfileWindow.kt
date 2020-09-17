package org.unq.ui.View

import org.unq.ui.ViewModel.UserDataModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class EditProfileWindow(owner: WindowOwner, model: UserDataModel): Dialog<UserDataModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"
        setMinWidth(400)
        Label(mainPanel) withText "Name"
        TextBox(mainPanel) with {
            width = 400
            bindTo("name")
        }

        Label(mainPanel) withText "Password"
        TextBox(mainPanel) with {
            width = 400
            bindTo("password")
        }

        Label(mainPanel) withText "Image"
        TextBox(mainPanel) with {
            width = 400
            bindTo("image")
        }

        Panel(mainPanel) with {
            asHorizontal()

            Button(it) with {
                alignCenter()
                width = 200
                var model = thisWindow.modelObject
                caption = "Accept"
                onClick {
                    if (model.name.isNullOrEmpty() || model.password.isNullOrEmpty() || model.image.isNullOrEmpty()) {
                        showError("You must complete all the fields")
                    } else {
                        accept()
                    }
                }
            }

            Button(it) with {
                alignCenter()
                width = 200
                caption = "Cancel"
                onClick {
                    cancel()
                }
            }
        }

    }
}
