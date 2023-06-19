package design

import client.ClientManager
import tornadofx.*
import java.util.*

import client.CommandInterpreter
import client.Task
import client.Task.*
import commandArguments.Response
import javafx.beans.property.SimpleStringProperty
import org.koin.core.component.*

class MyApp : App(LoginPage::class), KoinComponent{
//    val fxmlLoader = FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"))
//    val root = Parent(fxmlLoader.load())
//    val scene = Scene(root)



    companion object{

        var login =""
        val clientManager = ClientManager("localhost", 12345)
        val commandInterpreter = CommandInterpreter(clientManager)

            fun runCommand(command: String): Response {


            // Парсинг команды
            val (commandData, _) = commandInterpreter.interpret(command)

            // Проверка валидности аргмуентов
            commandData.arguments.forEach { argument ->
                if (argument.value.isNullOrEmpty()) {
                    print("Enter ${argument.name} (${argument.type}): ")
                    argument.value = readlnOrNull()
                }
            }

            // Запуск команды и получение ответа
            val task = Task(commandData)
            return task.execute(clientManager)
        }

        // Управление ресурсами
        var bundle= ResourceBundle.getBundle("interface", Locale("en", "EN"))
        var setBundle: ResourceBundle
            get() = bundle
            set(value) {
                bundle = value
            }

        // Логин пользователя текущей сессии
        var currLogin = ""
    }
}