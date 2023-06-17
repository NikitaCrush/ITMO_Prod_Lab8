package design

import tornadofx.*
import javax.*
import javax.swing.text.View

class WelcomePage : tornadofx.View("Welcome") {
    override val root = borderpane {
        //spacing = 10.0
        paddingAll = 20.0
        button("Let's start!") {
            action {
                replaceWith<LoginPage>(sizeToScene = true)
            }
        }
    }
}
