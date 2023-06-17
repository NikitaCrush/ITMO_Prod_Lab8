package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*
import java.util.*

class RegistrationPage : View() {
    // Подгружаем тексты из бандлов
    private val authLabelText = SimpleStringProperty(MyApp.bundle.getString("authLabel"))
    private val loginLabelText = SimpleStringProperty(MyApp.bundle.getString("loginFieldLabel"))
    private val passwordLabelText = SimpleStringProperty(MyApp.bundle.getString("passwordFieldLabel"))
    private val loginButtonText = SimpleStringProperty(MyApp.bundle.getString("loginButton"))
    private val registerButtonText = SimpleStringProperty(MyApp.bundle.getString("registerButton"))

    // Поля, которые будут хранить в себе введенные логин и пароль
    private val inputLogin = SimpleStringProperty("")
    private val inputPassword = SimpleStringProperty("")

    override val root = form {
        setPrefSize(840.0, 590.0)
        primaryStage.isResizable = false

        label(authLabelText) {
            style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 30.px
                padding = box(10.px, 20.px)
            }
        }
        fieldset() {
            alignment = Pos.TOP_CENTER
            label(loginLabelText).style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 20.px
                padding = box(10.px, 20.px)
            }
            field() {
                textfield(inputLogin).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
            label(passwordLabelText).style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 20.px
                padding = box(10.px, 20.px)
            }
            field() {
                textfield(inputPassword).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.TOP_CENTER) {
            val LoginButton = button() {
                label(loginButtonText).style {
                    setAlignment(Pos.CENTER_LEFT)
                    setTextAlignment(TextAlignment.CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                }
                action {
                    MyApp.currLogin = inputLogin.value
                    val command = "login ${inputLogin.value} ${inputPassword.value}"
                    val response = MyApp.runCommand(command)

                    val al = Alert(Alert.AlertType.INFORMATION, response.message).show()

                    replaceWith<MainPage>(sizeToScene = true)

                    inputLogin.value = ""
                    inputPassword.value = ""
                }
                style {
                    setTextAlignment(TextAlignment.CENTER)
                    setAlignment(Pos.TOP_LEFT)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                minHeight = 50.0
            }
        }
    }
}
