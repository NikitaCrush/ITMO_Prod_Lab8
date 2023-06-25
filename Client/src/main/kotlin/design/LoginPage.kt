package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*
import java.util.*
import javafx.scene.image.ImageView
import javafx.scene.image.Image

import data.User
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import utils.HashUtil
import javax.swing.ButtonGroup


class LoginPage : View() {

    // Подгружаем тексты из бандлов
    private val authLabelText = SimpleStringProperty(MyApp.bundle.getString("authLabel"))
    private val loginLabelText = SimpleStringProperty(MyApp.bundle.getString("loginFieldLabel"))
    private val passwordLabelText = SimpleStringProperty(MyApp.bundle.getString("passwordFieldLabel"))
    private val loginButtonText = SimpleStringProperty(MyApp.bundle.getString("loginButton"))
    private val registerButtonText = SimpleStringProperty(MyApp.bundle.getString("registerButton"))

    // Поля, которые будут хранить в себе введенные логин и пароль
    private val inputLogin = SimpleStringProperty("")
    private val inputPassword = SimpleStringProperty("")

    private val hashUtil = HashUtil()

    override val root = form {
        setPrefSize(850.0, 650.0)
        primaryStage.isResizable = false
        style {
            backgroundColor += Color.web("#454545")
        }
        vbox(70, Pos.TOP_LEFT) {
            val image = Image("иконка куроми.png")
            val pic = ImageView(image)
        }
        label(authLabelText) {
            style{
                setAlignment(Pos.CENTER)
                fontFamily = "Bodoni MT Condensed"
                fontSize = 70.px
                padding = box(10.px, 20.px)
                textFill = Color.web("dd2e72")
            }
        }
        fieldset() {
            alignment = Pos.TOP_CENTER
            label(loginLabelText).style {
                setAlignment(Pos.TOP_RIGHT)
                fontFamily = "Bodoni MT Condensed"
                fontSize = 30.px
                padding = box(10.px, 20.px)
                textFill = Color.web("dd2e72")
            }
            field() {
                textfield(inputLogin).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Bodoni MT Condensed"
                    fontSize = 20.px
                    textFill = Color.WHITE
                    backgroundColor += Color.web("#454545")
                }
            }
            label(passwordLabelText).style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Bodoni MT Condensed"
                fontSize = 30.px
                padding = box(10.px, 20.px)
                textFill = Color.web("dd2e72")
            }
            field() {
                textfield(inputPassword).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontSize = 20.px
                    fontFamily = "Bodoni MT Condensed"
                    textFill = Color.web("dd2e72")
                }
            }
        }
        hbox(50, Pos.TOP_CENTER) {
            val LoginButton = button() {
                label(loginButtonText).style {
                    setAlignment(Pos.CENTER_LEFT)
                    setTextAlignment(TextAlignment.CENTER)
                    textFill = Color.BLACK
                    fontFamily = "Bodoni MT Condensed"
                }
                action {
                    MyApp.login = inputLogin.value
                    MyApp.commandProcessor.commandInterpreter.setUser(
                        User(
                            inputLogin.value,
                            hashUtil.hashPassword(inputPassword.value)
                        )
                    )
                    MyApp.commandProcessor.runCommand("login")
                    replaceWith<MainPage>(sizeToScene = true)

                    inputLogin.value = ""
                    inputPassword.value = ""
                }

                style {
                    setTextAlignment(TextAlignment.CENTER)
                    setAlignment(Pos.TOP_LEFT)
                    textFill = Color.BLACK
                    padding = box(10.px, 20.px)
                }
                setStyle("-fx-background-color: #dd2e72;")
                minHeight = 50.0
            }
            val RegisterButton = button() {
                label(registerButtonText).style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.BLACK
                    fontFamily = "Bodoni MT Condensed"
                }
                style {
                    setTextAlignment(TextAlignment.CENTER)
                    textFill = Color.BLACK
                    padding = box(10.px, 20.px)
                }
                setStyle("-fx-background-color: #dd2e72;")
                minHeight = 50.0
                action {
                    MyApp.login = inputLogin.value
                    MyApp.commandProcessor.commandInterpreter.setUser(
                        User(
                            inputLogin.value,
                            hashUtil.hashPassword(inputPassword.value)
                        )
                    )
                    MyApp.commandProcessor.runCommand("register")
                    replaceWith<MainPage>(sizeToScene = true)

                    inputLogin.value = ""
                    inputPassword.value = ""
                }
            }
        }
        hbox(30, Pos.BOTTOM_CENTER) {
            val toggle = togglegroup {
                togglebutton("ru") {
                    style{
                        fontFamily = "Bodoni MT Condensed"
                        textFill = Color.BLACK
                    }
                    setStyle("-fx-background-color: #dd2e72;")
                    action {
                        setLocaleBundle("ru", "RU")
                    }
                }
                togglebutton("en") {
                    style{
                        fontFamily = "Bodoni MT Condensed"
                        textFill = Color.BLACK
                    }
                    setStyle("-fx-background-color: #dd2e72;")
                    action {
                        setLocaleBundle("en", "EN")
                    }
                }
            }
        }
        onRefresh()
    }

    fun setLocaleBundle(language: String, country: String) {
        MyApp.setBundle = ResourceBundle.getBundle("interface", Locale(language, country))
        authLabelText.set(MyApp.bundle.getString("authLabel"))
        loginLabelText.set(MyApp.bundle.getString("loginFieldLabel"))
        passwordLabelText.set(MyApp.bundle.getString("passwordFieldLabel"))
        loginButtonText.set(MyApp.bundle.getString("loginButton"))
        registerButtonText.set(MyApp.bundle.getString("registerButton"))
    }
}