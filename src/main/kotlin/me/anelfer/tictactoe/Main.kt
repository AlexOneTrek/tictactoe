package me.anelfer.tictactoe

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage
import javafx.scene.layout.StackPane

class Main : Application()
{
    val minH = 300.0
    val minW = 300.0

    override fun start(stage: Stage) {
        val lg = LogicGame()
        val canvas = ResizableCanvas(lg)
        val pane = StackPane()
        val btn = Button("Game over")

        val hvsh = Button("Human vs Human")
        val hvsb = Button("Human vs Bot")
        val bvsb = Button("Bot Vs Bot")

        btn.setId("endGame")

        pane.children.add(hvsh)
        pane.children.add(hvsb)
        pane.children.add(bvsb)

        hvsh.setMaxWidth(120.0)
        hvsb.setMaxWidth(120.0)
        bvsb.setMaxWidth(120.0)

        StackPane.setMargin(hvsh, Insets(0.0, 0.0, 80.0, 0.0))
        StackPane.setMargin(bvsb, Insets(80.0, 0.0, 0.0, 0.0))

        hvsh.setOnAction {
            println("HUM VS HUM")
            pane.children.remove(hvsh)
            pane.children.remove(hvsb)
            pane.children.remove(bvsb)

            lg.modeGame = 0

            pane.children.add(canvas)
            canvas.widthProperty().bind(pane.widthProperty())
            canvas.heightProperty().bind(pane.heightProperty())
        }
        hvsb.setOnAction {
            println("HUM VS BOT")
            pane.children.remove(hvsh)
            pane.children.remove(hvsb)
            pane.children.remove(bvsb)

            lg.modeGame = 1

            pane.children.add(canvas)
            canvas.widthProperty().bind(pane.widthProperty())
            canvas.heightProperty().bind(pane.heightProperty())
        }
        bvsb.setOnAction {
            println("BOT VS BOT")
            pane.children.remove(hvsh)
            pane.children.remove(hvsb)
            pane.children.remove(bvsb)

            lg.modeGame = 3

            pane.children.add(canvas)
            canvas.widthProperty().bind(pane.widthProperty())
            canvas.heightProperty().bind(pane.heightProperty())
        }

        pane.setOnMouseClicked{ event ->
            if (lg.victoryFlag == 0){
                lg.ClickOnSquare(event, canvas)
            }
            if (lg.victoryFlag == 1){
                btn.setMinHeight(100.0);
                btn.setMinWidth(150.0);
                btn.setOnAction {
                    lg.restartGame(canvas)
                    pane.children.remove(btn)

                    pane.children.add(hvsh)
                    pane.children.add(hvsb)
                    pane.children.add(bvsb)

                    pane.children.remove(canvas)
                }
                if (pane.children.size < 2){
                    pane.children.add(btn)
                }
            }
        }

        stage.title = "TicTacToe"
        stage.setHeight(600.0)
        stage.setWidth(600.0)

        stage.setMinWidth(minW)
        stage.setMinHeight(minH)

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
