package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*

class AddPage : View() {

    private val inputName = SimpleStringProperty()
    private val inputX = SimpleStringProperty()
    private val inputY = SimpleStringProperty()
    private val inputMinimalPoint = SimpleStringProperty()
    private val inputPQMin = SimpleStringProperty()
    private val inputPQMax = SimpleStringProperty()
    private val inputDifficulty = SimpleStringProperty()
    private val inputDName = SimpleStringProperty()
    private val inputDSSH = SimpleStringProperty()

    override val root = form {
        setPrefSize(840.0, 590.0)
        fieldset {
            field(MyApp.bundle.getString("name")) {
                textfield(inputName).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("cX")) {
                textfield(inputX).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("cY")) {
                textfield(inputY).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("minimalPoint")) {
                textfield(inputMinimalPoint).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("pQMin")) {
                textfield(inputPQMin).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("pQMax")) {
                textfield(inputPQMax).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field("${MyApp.bundle.getString("difficulty")}: \n[EASY,\nHARD,\nVERY_HARD," +
                    "\nIMPOSSIBLE,\nHOPELESS] or null") {
                textfield(inputDifficulty).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("dName")) {
                textfield(inputDName).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("dSSH")) {
                textfield(inputDSSH).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
        }.style{
            setMaxSize(750.0, 900.0)
            alignment = Pos.TOP_CENTER
        }

    }
}
