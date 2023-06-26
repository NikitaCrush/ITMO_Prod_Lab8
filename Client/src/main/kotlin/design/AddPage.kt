package design

import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*
import java.time.LocalDateTime
import javafx.scene.paint.Color
import kotlin.random.Random


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
    private val answerText = SimpleStringProperty()

    override val root = form {
        setPrefSize(900.0, 700.0)
        style {
            backgroundColor += Color.web("#dd2e72")
        }
        fieldset {
            field(MyApp.bundle.getString("name")) {
                textfield(inputName).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field(MyApp.bundle.getString("cX")) {
                textfield(inputX).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field(MyApp.bundle.getString("cY")) {
                textfield(inputY).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field(MyApp.bundle.getString("minimalPoint")) {
                textfield(inputMinimalPoint).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field(MyApp.bundle.getString("pQMin")) {
                textfield(inputPQMin).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field("${MyApp.bundle.getString("difficulty")}: \n[EASY,\nNORMAL,\nTERRIBLE] or null") {
                textfield(inputDifficulty).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field(MyApp.bundle.getString("dName")) {
                textfield(inputDName).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
            field(MyApp.bundle.getString("dSSH")) {
                textfield(inputDSSH).useMaxWidth
                style {
                    fontSize = 25.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
        }.style{
            setMaxSize(750.0, 900.0)
            backgroundColor += Color.web("#dd2e72")
            alignment = Pos.TOP_CENTER
        }
        hbox(50, Pos.BOTTOM_RIGHT) {
            button("Create") {
                style {
                    setStyle("-fx-background-color: #454545;")
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.web("dd2e72")
                    backgroundColor += Color.BLACK
                    fontFamily = "Bodoni MT Condensed"
                    padding = box(10.px, 20.px)
                }
                action {
                    val labWork = LabWork(
                        Random.nextLong(1, 1000000),
                        inputName.value,
                        Coordinates(inputX.value.toLong(), inputY.value.toDouble()),
                        LocalDateTime.now(),
                        inputMinimalPoint.value.toInt(),
                        inputPQMin.value.toInt(),
                        Difficulty.valueOf(inputDifficulty.value),
                        Discipline(inputDName.value.toString(), inputDSSH.value.toLong()),
                        MyApp.login
                    )
                    MyApp.commandProcessor.commandInterpreter.setLabWork(labWork)
                    MyApp.commandProcessor.runCommand("add")
                    replaceWith<MainPage>(sizeToScene = false)
                }
            }
            label(answerText).style {
                setAlignment(Pos.TOP_CENTER)
                padding = box(30.px, 20.px)
                fontSize = 25.px
                fontFamily = "Bodoni MT Condensed"
                textFill = Color.web("dd2e72")
            }
        }
    }
}
