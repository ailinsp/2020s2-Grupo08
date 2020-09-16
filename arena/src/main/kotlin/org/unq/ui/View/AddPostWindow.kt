package org.unq.ui.View

import org.unq.ui.ViewModel.DraftPostModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class AddPostWindow (owner: WindowOwner, model: DraftPostModel): Dialog<DraftPostModel>(owner, model){

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
                    showError("You must complete all fields")
                } else{
                    accept()
                }
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick { cancel() }
        }
    }
}