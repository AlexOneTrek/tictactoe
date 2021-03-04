package me.anelfer.tictactoe

import javafx.scene.layout.Pane
import java.util.*
import kotlin.math.floor

class LogicGame(canvas: ResizableCanvas) {
    val cnvs = canvas
    fun ClickOnSquare(pane: Pane){
        val field:MutableMap<Int, Boolean> = mutableMapOf(
            1 to false,
            2 to false,
            3 to false,
            4 to false,
            5 to false,
            6 to false,
            7 to false,
            8 to false,
            9 to false
        )

        var crossOrToe = 0 //0 = 0 , 1 = X

        val playerFieldMaps: MutableMap<Int, Int> = mutableMapOf(
            1 to 3,
            2 to 3,
            3 to 3,
            4 to 3,
            5 to 3,
            6 to 3,
            7 to 3,
            8 to 3,
            9 to 3
        )

        pane.setOnMouseClicked{ event ->
            if (event.sceneX >= cnvs.left &&
                event.sceneX <= cnvs.right &&
                event.sceneY >= cnvs.top &&
                event.sceneY <= cnvs.bottom

            )
            {
                val clickShift: Array<Double> = arrayOf(
                    event.sceneX - cnvs.left,
                    event.sceneY - cnvs.top
                )
                println(Arrays.toString(clickShift))

                val row: Int = (floor(clickShift[1] / cnvs.sqLen) + 1).toInt() // Y
                val column: Int = (floor(clickShift[0] / cnvs.sqLen) + 1).toInt() // X

//                println("row " + row)
//                println("column " + column)

                val numField: Int = 1 + ((row - 1) * 3) + (column - 1 )

                println("nu_fi " + numField)
                println(field)
                println("PlayerField: " + playerFieldMaps)

                if (field.get(numField) == false){
                    field[numField] = false
                    for ( i in 1..3){
                        for (j in 1..3){
                            if (row == i && column == j){
                                val clickX = (cnvs.left + (j * cnvs.sqLen)) - cnvs.sqLen / 2
                                val clickY = (cnvs.top + (i * cnvs.sqLen)) - cnvs.sqLen / 2

                                println("row " + i)
                                println("column " + j)

                                println("ClX " + clickX)
                                println("ClY " + clickY)

                                if (crossOrToe == 0){
                                    cnvs.graphicsContext2D.strokeOval(
                                        clickX - cnvs.sqLen / 4,
                                        clickY - cnvs.sqLen / 4,
                                        cnvs.sqLen / 2,
                                        cnvs.sqLen / 2)
                                    crossOrToe = 1 - crossOrToe
                                    playerFieldMaps[numField] = 0
                                    definitionVictory(field)
                                }else{
                                    cnvs.graphicsContext2D.strokeLine(
                                        clickX - cnvs.sqLen / 4,
                                        clickY - cnvs.sqLen / 4,
                                        clickX + cnvs.sqLen / 4,
                                        clickY + cnvs.sqLen / 4)
                                    cnvs.graphicsContext2D.strokeLine(
                                        clickX + cnvs.sqLen / 4,
                                        clickY - cnvs.sqLen / 4,
                                        clickX - cnvs.sqLen / 4,
                                        clickY + cnvs.sqLen / 4)
                                    crossOrToe = 1 - crossOrToe
                                    playerFieldMaps[numField] = 1
                                    definitionVictory(field)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun definitionVictory(field: MutableMap<Int, Boolean>) {
        println(field)
    }

}