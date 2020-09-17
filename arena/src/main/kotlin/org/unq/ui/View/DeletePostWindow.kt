package org.unq.ui.View

import org.unq.ui.ViewModel.PostModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeletePostWindow (owner: WindowOwner, model: PostModel): Dialog<PostModel>(owner, model) {
    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel) with {
            text = "Are you sure you want to delete the post with id: ${modelObject.id} ?"


            Panel(mainPanel) with {
                asHorizontal()

                Button(it) with {
                    alignCenter()
                    width = 150
                    caption = "Yes"
                    onClick {
                        accept()
                    }
                }

                Button(it) with {
                    alignCenter()
                    width = 150
                    caption = "No"
                    onClick { cancel() }
                }
            }


        }
    }
}

