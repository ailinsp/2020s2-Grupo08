package org.unq.ui.User

import org.unq.ui.model.DraftPostModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class UserAddPostView (owner: WindowOwner, model: DraftPostModel): Dialog<DraftPostModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Add and Post"
        setMinWidth(300)

        Label(mainPanel) withText "Portrait"
        TextBox(mainPanel) with{
            bindTo("portrait")
        }
        Label(mainPanel) withText "Landscape"
        TextBox(mainPanel) with{
            bindTo("landscape")
        }
        Label(mainPanel) withText "Description"
        TextBox(mainPanel) with{
            bindTo("description")
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick{
                if (modelObject.description.isEmpty() || modelObject.landscape.isEmpty() || modelObject.portrait.isEmpty()){
                    showError("all fields are required")
                } else{
                    accept()
                }
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick { cancel() }
        }

        /*
        Panel(mainPanel) with {
            asColumns(4)
            Label(it) withText "Portrait"
            TextBox(it) with {
                width = 150
                bindTo("portrait")
            }

            asColumns(2)
            Label(it) withText "Landscape"
            TextBox(it) with {
                width = 150
                bindTo("landscape")
            }

            asColumns(2)
            Label(it) withText "Description"
            TextBox(it) with {
                width = 150
                bindTo("description")
            }
        }

        Panel(mainPanel) with {
            asColumns(2)
            Button(it) with {
                caption = "Accept"
                width = 175
                onClick {
                    if (modelObject.description.isEmpty()) {
                        showError("Se necesita un titulo =S")
                    } else {
                        accept()
                    }
                }
            }

            Button(it) with {
                caption = "Cancel"
                width = 175
                onClick {
                    thisWindow.close()
                    UserView(owner, InstagramModel()).open()
                }
            }
        }*/
    }

}