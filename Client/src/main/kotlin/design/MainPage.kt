package design

import data.Coordinates
import data.Difficulty
import data.Discipline
import data.LabWork
import java.util.regex.Pattern
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.paint.Color
import tornadofx.*
import java.time.LocalDateTime


class MainPage() : View() {

    private val tableData: ObservableList<LabWork> = mutableListOf<LabWork>().asObservable()
    private val login = SimpleStringProperty()

    fun parseShowResponse(response: String): ArrayList<LabWork> {
        var outputList = ArrayList<LabWork>()
        val pattern = Pattern.compile("=\\w+")
        val parts = response.split("\n")
        for (part in parts) {
            val m = pattern.matcher(part)
            val subParts = ArrayList<String>()
            while (m.find()) {
                val group = m.group()
                subParts.add(group.substring(1, group.length))
            }
            val labWorkObj = LabWork(
                id=subParts[0].toLong(),
                name=subParts[1],
                coordinates=Coordinates(subParts[3].toLong(), subParts[4].toDouble()),
                LocalDateTime.now(),
                minimalPoint = subParts[6].toInt(),
                personalQualitiesMinimum = subParts[7].toInt(),
                difficulty = Difficulty.valueOf(subParts[8]),
                discipline = Discipline(subParts[10], subParts[11].toLong()),
                owner = subParts[12]
            )
            outputList.add(labWorkObj)
        }

        return outputList
    }

    override val root = tabpane {
        setPrefSize(890.0, 650.0)
        style {
            backgroundColor += Color.web("#4f4f4f")
        }
        tab("Table") {
            setPrefSize(900.0, 700.0)
            borderpane() {
                primaryStage.isResizable = false
                right {
                    scrollpane {
                        style {
                            fitToHeight = true
                            fitToWidth = true
                        }
                        tableview(tableData) {
                            setPrefSize(850.0, 650.0)
                            style {
                                backgroundColor += Color.web("#454545")
                                fontFamily = "Bodoni MT Condensed"
                            }
                            isEditable = false
                            column(MyApp.bundle.getString("idColumnName"), LabWork::getId)
                            column(MyApp.bundle.getString("nameColumnName"), LabWork::getName)
                            column("X", LabWork::getCoordinates).cellFormat {
                                text = it.getX().toString()
                            }
                            column("Y", LabWork::getCoordinates).cellFormat {
                                text = it.getY().toString()
                            }
                            column(MyApp.bundle.getString("creationDateColumnName"), LabWork::getCreationDate)
                            column(MyApp.bundle.getString("minPointColumnName"), LabWork::getMinimalPoint)
                            column(MyApp.bundle.getString("persQMin"), LabWork::getPersonalQualitiesMinimum)
                            column(MyApp.bundle.getString("difficultyColumnName"), LabWork::getDifficulty)
                            column(MyApp.bundle.getString("disciplineNameColumnName"), LabWork::getDiscipline).cellFormat {
                                text = it.getName()
                            }
                            column(MyApp.bundle.getString("selfStudyHoursColumnName"), LabWork::getDiscipline).cellFormat {
                                text = it.getSelfStudyHours().toString()
                            }
                            column(MyApp.bundle.getString("ownerColumnName"), LabWork::getOwner)
                        }
                    }
                }
                left {
                    maxWidth = 240.0
                    vbox {
                        login.set(MyApp.login)
                        label(login).style {
                            setAlignment(Pos.TOP_CENTER)
                            fontFamily = "Bodoni MT Condensed"
                            fontSize = 30.px
                            padding = box(10.px, 20.px)
                        }
                        style {
                            setAlignment(Pos.TOP_LEFT)
                            padding = box(30.px, 20.px)
                            setAlignment(Pos.TOP_CENTER)
                        }
                        button(MyApp.bundle.getString("helpButton")) {
                            style {
                                textFill = Color.WHITE
                                fontFamily = "Bodoni MT Condensed"
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                val alert = Alert(Alert.AlertType.INFORMATION)
                                alert.setTitle("Information about commands")
                                alert.setHeaderText("Commands:")
                                alert.setContentText("""
                                                    help
                                                    add
                                                    clear
                                                    exit
                                                    remove by Id
                                                    log out
                                                """.trimIndent())

                                val result = alert.showAndWait()
                                if (result.isPresent && result.get() == ButtonType.OK) {
                                    alert.close()
                                }
//
                            }
                        }
                        button(MyApp.bundle.getString("showButton")) {
                            style {
                                textFill = Color.WHITE
                                fontFamily = "Bodoni MT Condensed"
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                tableData.asObservable().removeAll()
                                val answer = MyApp.commandProcessor.runCommand("show")
                                answer.message
                                // Parse
                                val labWorks = parseShowResponse(answer.message)

                                tableData.setAll(labWorks.asObservable())
                            }
                        }
                        button(MyApp.bundle.getString("addEntityButton")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(AddPage::class)
                            }
                        }
                        button(MyApp.bundle.getString("removeEntityButton")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(RemoveByIdPage::class)
                            }
                        }
                        button(MyApp.bundle.getString("logOutButton")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                MyApp.commandProcessor.runCommand("logout")
                                replaceWith<LoginPage>(sizeToScene = true)
                            }
                        }
                        button(MyApp.bundle.getString("exitButton")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action { close() }
                        }
                        onRefresh()
                    }
                }
            }
        }
    }
}

