package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*


class RemoveByIdPage : View() {
    private val input = SimpleStringProperty()

    override val root= form{
        fieldset {
            alignment = Pos.TOP_CENTER
            field(MyApp.bundle.getString("enterId")) {
                textfield(input).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.BOTTOM_RIGHT){
            button(MyApp.bundle.getString("Execute")) {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    MyApp.commandProcessor.commandInterpreter.interpret("remove_by_id -${input.value}")
                    input.value=""
                }
            }
        }
    }
}