package me.anelfer.tictactoe

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.scene.layout.Pane
import tornadofx.getProperty
import kotlin.reflect.typeOf

class Main : Application()
{
    val minH = 300.0
    val minW = 300.0

    override fun start(stage: Stage) {
        val canvas = ResizableCanvas()
        val pane = Pane()
        val lg = LogicGame(canvas)
        lg.ClickOnSquare(pane)

        stage.title = "TicTacToe"
        stage.setHeight(600.0)
        stage.setWidth(600.0)

        stage.setMinWidth(minW)
        stage.setMinHeight(minH)

        pane.children.add(canvas)

        canvas.widthProperty().bind(pane.widthProperty())
        canvas.heightProperty().bind(pane.heightProperty())

        stage.scene = Scene(pane)
        stage.show()

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
