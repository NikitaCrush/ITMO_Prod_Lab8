package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*

class HelpPage: View() {
    private val answerText= SimpleStringProperty("")

    override val root= vbox {
        setPrefSize(840.0, 300.0)
        answerText.set((MyApp.runCommand("help")).result)
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
    }
}
