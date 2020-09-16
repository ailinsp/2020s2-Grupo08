package org.unq.ui.View

import org.unq.ui.ViewModel.InstagramModel
import org.unq.ui.ViewModel.UserDataModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class EditProfileWindow(owner: WindowOwner, model: InstagramModel): Dialog<InstagramModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"

        Label(mainPanel) withText "Name"
        TextBox(mainPanel) with {

            bindTo("name")
        }

        Label(mainPanel) withText "Password"
        TextBox(mainPanel) with {
            bindTo("password")
        }

        Label(mainPanel) withText "Image"
        TextBox(mainPanel) with {
            bindTo("image")
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick{
                if(modelObject.name.isNullOrEmpty() || modelObject.password.isNullOrEmpty() || modelObject.image.isNullOrEmpty() ){
                    showError("You must complete all the fields")
                }
                else{
                    val user = UserDataModel(modelObject.name,modelObject.password,modelObject.image)
                    modelObject.editProfile(user)
                    thisWindow.close()
                    UserWindow(owner,this@EditProfileWindow.modelObject).open()
                }
            }
        }

        Button(mainPanel) with{
            caption = "Cancel"
            onClick{
                thisWindow.close()
                UserWindow(owner, this@EditProfileWindow.modelObject).open()
            }
        }
    }
}
