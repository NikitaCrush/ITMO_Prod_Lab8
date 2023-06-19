package design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*
import java.util.*

import data.User
import utils.HashUtil


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
        setPrefSize(840.0, 590.0)
        primaryStage.isResizable = false

        label(authLabelText) {
            style{
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
                    MyApp.login=inputLogin.value
                    MyApp.commandInterpreter.setUser(
                        User(
                            inputLogin.value,
                            hashUtil.hashPassword(inputPassword.value)
                        )
                    )
                    MyApp.runCommand("login")
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
            val RegisterButton = button() {
                label(registerButtonText).style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                }
                style {
                    setTextAlignment(TextAlignment.CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                minHeight = 50.0
                action {
                    MyApp.login=inputLogin.value
                    MyApp.commandInterpreter.setUser(
                        User(
                            inputLogin.value,
                            hashUtil.hashPassword(inputPassword.value)
                        )
                    )
                    MyApp.runCommand("register")
                    replaceWith<MainPage>(sizeToScene = true)

                    inputLogin.value = ""
                    inputPassword.value = ""
                }
            }
        }
        hbox(30, Pos.BOTTOM_CENTER) {
            val toggle = togglegroup {
                togglebutton("ru") {
                    action {
                        setLocaleBundle("ru", "RU")
                    }
                }
                togglebutton("en") {
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