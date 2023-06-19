package design

import design.LoginPage
import com.sun.javafx.binding.BidirectionalBinding.bind
import data.LabWork
import javafx.animation.AnimationTimer
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import tornadofx.*
import tornadofx.Stylesheet.Companion.arrow
import tornadofx.Stylesheet.Companion.imageView
import tornadofx.Stylesheet.Companion.tab
import java.awt.Point


class MainPage() : View() {

    private val tableData: ObservableList<LabWork> = mutableListOf<LabWork>().asObservable()
    private val login = SimpleStringProperty()

    override val root = tabpane {
        tab("Table") {
            borderpane() {
                primaryStage.isResizable = false
                right {
                    scrollpane {
                        style {
                            fitToHeight = true
                            fitToWidth = true
                        }
                        tableview(tableData) {
                            setPrefSize(1500.0, 900.0)
                            style {
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
                            column(MyApp.bundle.getString("minPointColumnName"), LabWork::getMinimalPoint)
                            column(MyApp.bundle.getString("difficultyColumnName"), LabWork::getDifficulty)
                            column(MyApp.bundle.getString("disciplineNameColumnName"), LabWork::getDiscipline).cellFormat {
                                text = it.getName().toString()
                            }
                            column(MyApp.bundle.getString("selfStudyHoursColumnName"), LabWork::getDiscipline).cellFormat {
                                text = it.getSelfStudyHours().toString()
                            }
                        }
                    }
                }

                left {
                    maxWidth = 240.0
                    vbox {
                        login.set(MyApp.login)
                        label(login).style {
                            setAlignment(Pos.TOP_CENTER)
                            fontFamily = "Small capital"
                            fontSize = 20.px
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
                                                    show
                                                    add
                                                    save
                                                    exit
                                                    remove by Id
                                                """.trimIndent())

                                val result = alert.showAndWait()
                                if (result.isPresent && result.get() == ButtonType.OK) {
                                    alert.close()
                                }
//                                val alert = Alert(Alert.AlertType.INFORMATION)
//                                alert.setTitle("Information about commands")
//                                alert.setHeaderText("Commands:")
//                                alert.setContentText("help \n" + "show \n" + "add \n" + "save \n" + "exit \n" + "remove by Id")
//
//                                alert.showAndWait()
//                                openInternalWindow(HelpPage::class)
                            }
                        }
                        onRefresh()
                    }
                }
            }
        }
    }
}

