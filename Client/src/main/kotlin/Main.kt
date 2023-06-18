import tornadofx.*
import design.MyApp
import client.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import utils.LabWorkReader
import utils.LabWorkValidator
import utils.ProfileReader

val appModule = module {
    single { ClientManager("localhost", 12345) }
    single { LabWorkReader({ readlnOrNull() ?: throw IllegalStateException("No input provided") }, get()) }
    single { LabWorkValidator() }
    single { ProfileReader() }
    single { CommandInterpreter(get()) }
}

fun main() {
    startKoin {
        modules(appModule)
    }

    launch<MyApp>()
}


