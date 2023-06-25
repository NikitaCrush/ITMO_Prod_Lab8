/*package client

import data.Messages
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CommandProcessor : KoinComponent {

    private val clientManager: ClientManager by inject()
    private val commandInterpreter: CommandInterpreter by inject()

    /**
     * Start processing user input commands
     */

    fun start() {
        clientManager.setCommandInterpreter(commandInterpreter)
        clientManager.connect()

        //println(Messages.WELCOME)
        //println(Messages.ENTER_HELP)

        while (true) {
            print("> ")
            val input = readlnOrNull() ?: continue
            if (input.lowercase() == "exit") {
                clientManager.disconnect()
                break
            }

            try {
                val (commandData, _) = commandInterpreter.interpret(input)

                if (commandData.commandName != "register" && commandData.commandName != "login" && clientManager.token == null) {
                    println("Please login before executing commands.")
                    continue
                }

                val task = Task(commandData)

                commandData.arguments.forEach { argument ->
                    if (argument.value.isNullOrEmpty()) {
                        print("Enter ${argument.name} (${argument.type}): ")
                        argument.value = readlnOrNull()
                    }
                }

                val response = task.execute(clientManager)
                println(response.message)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
        clientManager.disconnect()
    }
}
*/

package client

import commandArguments.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CommandProcessor : KoinComponent {
    val commandInterpreter: CommandInterpreter by inject()
    private val clientManager: ClientManager by inject()

    constructor() {
        initClientManagerConnection()
    }

    private fun initClientManagerConnection() {
        commandInterpreter.clientManager.connect()
    }

    private fun processExit() {
        println("Disconnecting server")
        clientManager.disconnect()
    }

    fun runCommand(command: String): Response {
        val command_ = command.lowercase()

        if (command_ == "exit")
            processExit()

        try {
            // Parse command
            val (commandData, _) = commandInterpreter.interpret(command_)

            // Skip login or registration check
            // ...

            // Create task object for command execution
            val task = Task(commandData)
            val response = task.execute(commandInterpreter.clientManager)

            // Print response message
            println(response.message)
            return response
        }
        catch (e: IllegalArgumentException) {
            println(e.message)
        }
        catch (e: IllegalStateException) {
            println(e.message)
        }
        catch (e: Exception) {
            println("Unrecognized exception: ${e.message}")
        }
        return Response(false, "Error")
    }
}
