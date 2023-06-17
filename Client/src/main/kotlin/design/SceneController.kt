//package design
//
//import javafx.event.ActionEvent
//import javafx.scene.Node
//import javafx.scene.Parent
//import javafx.scene.Scene
//import javafx.scene.layout.VBox
//import javafx.stage.Stage
//import sun.tools.jconsole.inspector.Utils.getClass
//
//abstract class SceneController {
//    private var stage: Stage = Stage()
//    private var root: Parent = VBox()
//    private var scene: Scene = Scene(root)
//
//    fun switchToScene(event: ActionEvent, nameOfScene: String) {
//        val root = FXMLLoader.load<Any>(javaClass.getResource(nameOfScene))
//        stage = (event.source as Node).scene.window as Stage
//        scene = Scene(root)
//        stage.scene = scene
//        stage.show()
//    }
//}