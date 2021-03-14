package me.anelfer.tictactoe

import javafx.scene.input.MouseEvent
import kotlin.math.floor

class LogicGame{
    var playerFieldMap: MutableMap<Int, Int> = mutableMapOf(
        1 to 0,
        2 to 0,
        3 to 0,
        4 to 0,
        5 to 0,
        6 to 0,
        7 to 0,
        8 to 0,
        9 to 0
    )
    var crossOrToe = 0 //0 = 0 , 1 = X
    var victoryFlag = 0
    var victoryNumField: MutableList<Int> = mutableListOf()

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

            val row: Int = (floor(clickShift[1] / cnvs.sqLen) + 1).toInt() // Y
            val column: Int = (floor(clickShift[0] / cnvs.sqLen) + 1).toInt() // X

            val numField: Int = 1 + ((row - 1) * 3) + (column - 1 )

            if (playerFieldMap.get(numField) == 0){
                for ( i in 1..3){
                    for (j in 1..3){
                        if (row == i && column == j){
                            val clickX = (cnvs.left + (j * cnvs.sqLen)) - cnvs.sqLen / 2
                            val clickY = (cnvs.top + (i * cnvs.sqLen)) - cnvs.sqLen / 2

                            if (crossOrToe == 0){
                                cnvs.drawToe(clickX, clickY)
                                crossOrToe = 1 - crossOrToe
                                playerFieldMap[numField] = -1
                                detectVictory(cnvs)
                            }else{
                                cnvs.drawCross(clickX, clickY)
                                crossOrToe = 1 - crossOrToe
                                playerFieldMap[numField] = 1
                                detectVictory(cnvs)
                            }
                        }
                    }
                }
            }
        }
    }

    fun detectVictory(cnvs: ResizableCanvas) {
        val victorySum: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0)
        val victoryCell: MutableList<MutableList<Int>> = mutableListOf(
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        for (i in 1..9){
            // Sum row
            if (i < 4){
                victorySum[0] += playerFieldMap[i]!!
                victoryCell[0].add(i)
            }
            if (i in 4..6){
                victorySum[1] += playerFieldMap[i]!!
                victoryCell[1].add(i)
            }
            if (i in 7..10){
                victorySum[2] += playerFieldMap[i]!!
                victoryCell[2].add(i)
            }
            //sum column
            if (i == 1 || i == 4 || i == 7){
                victorySum[3] += playerFieldMap[i]!!
                victoryCell[3].add(i)
            }
            if (i == 2 || i == 5 || i == 8){
                victorySum[4] += playerFieldMap[i]!!
                victoryCell[4].add(i)
            }
            if (i == 3 || i == 6 || i == 9){
                victorySum[5] += playerFieldMap[i]!!
                victoryCell[5].add(i)
            }

            //sum diagonal
            if (i == 1 || i == 5 || i == 9){
                victorySum[6] += playerFieldMap[i]!!
                victoryCell[6].add(i)
            }
            if (i == 3 || i == 5 || i == 7){
                victorySum[7] += playerFieldMap[i]!!
                victoryCell[7].add(i)
            }
        }

        for (i in 0..7){
            if (victorySum[i] == 3){
                victoryFlag = 1
                victoryNumField = victoryCell[i]
                cnvs.victoryLine(victoryNumField)
            } else if (victorySum[i] == -3){
                victoryFlag = 1
                victoryNumField = victoryCell[i]
                cnvs.victoryLine(victoryNumField)
            }
        }

        if (playerFieldMap.values.all { it != 0 }){
            victoryFlag = 1
            println("FULL / DRAW")
        }

    }

    fun restartGame(cnvs: ResizableCanvas){
        playerFieldMap = mutableMapOf(
            1 to 0,
            2 to 0,
            3 to 0,
            4 to 0,
            5 to 0,
            6 to 0,
            7 to 0,
            8 to 0,
            9 to 0
        )
        crossOrToe = 0
        victoryFlag = 0
        victoryNumField = mutableListOf()

        cnvs.draw()
    }
}