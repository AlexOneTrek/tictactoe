package me.anelfer.tictactoe

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage
import javafx.scene.layout.Pane

class Main : Application()
{
    val minH = 300.0
    val minW = 300.0

    override fun start(stage: Stage) {
        val lg = LogicGame()
        val canvas = ResizableCanvas(lg)
        val pane = Pane()
        val btn = Button("Game over")

        pane.setOnMouseClicked{ event ->
            if (lg.victoryFlag == 0){
                lg.ClickOnSquare(event, canvas)
            }
            if (lg.victoryFlag == 1){
                btn.setOnAction {
                    lg.restartGame(canvas)
                    pane.children.remove(btn)
                }
                pane.children.add(btn)
            }
        }

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
