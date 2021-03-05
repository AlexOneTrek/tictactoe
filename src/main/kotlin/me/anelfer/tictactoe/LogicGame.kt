package me.anelfer.tictactoe

import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import java.util.*
import kotlin.math.floor

class LogicGame{
    val playerFieldMap: MutableMap<Int, Int> = mutableMapOf(
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
    var crossOrToe = 0 //0 = 0 , 1 = X

    fun ClickOnSquare(event: MouseEvent, cnvs: ResizableCanvas){
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
//                println(field)
//                println("PlayerField: " + playerFieldMaps)

            if (playerFieldMap.get(numField) == 3){
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
                                cnvs.drawToe(clickX, clickY)
                                crossOrToe = 1 - crossOrToe
                                playerFieldMap[numField] = 0
                                detectVictory(playerFieldMap)
                            }else{
                                cnvs.drawCross(clickX, clickY)
                                crossOrToe = 1 - crossOrToe
                                playerFieldMap[numField] = 1
                                detectVictory(playerFieldMap)
                            }
                        }
                    }
                }
            }
        }
    }

    fun detectVictory(playerFieldMap: MutableMap<Int, Int>) {
        println(playerFieldMap)
    }

}