package org.unq.ui.View

import org.unq.ui.ViewModel.UserDataModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class EditProfileWindow(owner: WindowOwner, model: UserDataModel): Dialog<UserDataModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"
        setMinWidth(400)
        Label(mainPanel) withText "Name"
        TextBox(mainPanel) with {
            width= 400
            bindTo("name")
        }

        Label(mainPanel) withText "Password"
        TextBox(mainPanel) with {
            width = 400
            bindTo("password")
        }

        Label(mainPanel) withText "Image"
        TextBox(mainPanel) with {
            width= 400
            bindTo("image")
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick{
                if(modelObject.name.isNullOrEmpty() || modelObject.password.isNullOrEmpty() || modelObject.image.isNullOrEmpty() ){
                    showError("You must complete all the fields")
                }
                else{
                   accept()
                }
            }
        }

        Button(mainPanel) with{
            caption = "Cancel"
            onClick{
               cancel()
            }
        }
    }
}
