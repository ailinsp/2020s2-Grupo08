package org.unq.ui.View

import org.unq.ui.ViewModel.DraftPostModel
import org.uqbar.arena.kotlin.extensions.bindTo
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.Dialog

class EditPostWindow(owner: WindowOwner, model: DraftPostModel): Dialog<DraftPostModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Post"
        setMinWidth(200)

        Label(mainPanel) with {
            text ="Portrait"
        }
        TextBox(mainPanel) with{
            bindTo("portrait")
        }

        Label(mainPanel) with {
            text ="Landscape"
        }
        TextBox(mainPanel) with{
            bindTo("landscape")
        }

        Label(mainPanel) with {
            text ="Description"
        }
        TextBox(mainPanel) with{
            bindTo("description")
        }

        Button(mainPanel) with{
            caption = "Accept"
            width = 200
            onClick{
                if(modelObject.landscape.isNullOrEmpty() || modelObject.portrait.isNullOrEmpty() || modelObject.description.isNullOrEmpty() ){
                    showError("You must complete all fields")
                }
                else{
                    accept()
                }
            }
        }

        Button(mainPanel) with{
            caption ="Cancel"
            onClick{ cancel() }
        }

    }

}
