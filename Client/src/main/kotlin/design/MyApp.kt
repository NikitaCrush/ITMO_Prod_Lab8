package design

import client.ClientManager
import tornadofx.*
import java.util.*

import client.CommandInterpreter
import client.CommandProcessor
import client.Task
import client.Task.*
import commandArguments.Response
import javafx.beans.property.SimpleStringProperty
import org.koin.core.component.*

class MyApp : App(LoginPage::class), KoinComponent{
    companion object{

        var login = ""
        val commandProcessor = CommandProcessor()

        /*
        val clientManager = ClientManager("localhost", 12345)
        val commandInterpreter = CommandInterpreter(clientManager)
        val commandProcessor = CommandProcessor()
        */

        /*fun runCommand(command: String): Response {
            // Парсинг команды
            val (commandData, _) = commandInterpreter.interpret(command)
            val task = Task(commandData)

            // Проверка валидности аргмуентов
            commandData.arguments.forEach { argument ->
                if (argument.value.isNullOrEmpty()) {
                    print("Enter ${argument.name} (${argument.type}): ")
                    argument.value = readlnOrNull()
                }
            }

            // Запуск команды и получение ответа
            val response = task.execute(clientManager)
            println(response.message)
                return response
        } */

        // Управление ресурсами
        var bundle = ResourceBundle.getBundle("interface", Locale("en", "EN"))
        var setBundle: ResourceBundle
            get() = bundle
            set(value) {
                bundle = value
            }
    }
}