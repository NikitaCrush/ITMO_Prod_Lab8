package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*


class RemoveByIdPage : View() {
    private val input = SimpleStringProperty()

    override val root= form {
        setPrefSize(500.0, 300.0)
        style {
            backgroundColor += Color.web("#4f4f4f")
        }
        fieldset {
            alignment = Pos.TOP_CENTER
            field(MyApp.bundle.getString("enterId")) {
                textfield(input).useMaxWidth
                style {
                    setAlignment(Pos.TOP_LEFT)
                    textFill = Color.BLACK
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    padding = box(10.px, 20.px)
                }
                minHeight = 50.0
            }
            hbox{
                button("execute") {
                    style {
                        setAlignment(Pos.TOP_CENTER)
                        textFill = Color.WHITE
                        padding = box(10.px, 20.px)
                    }
                    setStyle("-fx-background-color: #dd2e72;")
                    action {
                        MyApp.commandProcessor.runCommand("remove_by_id ${input.value}")
                        replaceWith<MainPage>(sizeToScene = true)
                    }
                }
            }
        }
    }
}