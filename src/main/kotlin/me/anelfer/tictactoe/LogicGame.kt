package me.anelfer.tictactoe

import javafx.scene.input.MouseEvent
import kotlin.math.floor
import kotlin.random.Random

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
    var modeGame = -1 // 0 - human vs human, 1 - human vs human, 3 - bot vs bot

    fun humanVsHuman(event: MouseEvent, cnvs: ResizableCanvas){
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
                if (modeGame == 0) {
                    if (crossOrToe == 0){
                        cnvs.drawToe(numField)
                        crossOrToe = 1 - crossOrToe
                        playerFieldMap[numField] = -1
                        detectVictory(cnvs)
                    }
                    else{
                        cnvs.drawCross(numField)
                        crossOrToe = 1 - crossOrToe
                        playerFieldMap[numField] = 1
                        detectVictory(cnvs)
                    }
                } else if (modeGame == 1){
                    cnvs.drawToe(numField)
                    crossOrToe = 1 - crossOrToe
                    playerFieldMap[numField] = -1
                    detectVictory(cnvs)

                    if (victoryFlag != 1){
                        botGame(cnvs)
                    }
                } else if (modeGame == 3) {
                    botGame(cnvs)
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

    fun botGame(cnvs: ResizableCanvas){
        val randomInt = Random.nextInt(0, 8)
        var greaterThanZero = 0
        println(randomInt)
        println(playerFieldMap)
        for (i in 0..8){
            if (playerFieldMap[i] == 0){
                greaterThanZero++
            }
        }
        if (greaterThanZero > 0){
            if (playerFieldMap[randomInt] == 0){
                if(crossOrToe == 0){
                    playerFieldMap[randomInt] = -1
                    cnvs.drawToe(randomInt)
                    crossOrToe = 1 - crossOrToe
                    detectVictory(cnvs)
                } else {
                    playerFieldMap[randomInt] = 1
                    cnvs.drawCross(randomInt)
                    crossOrToe = 1 - crossOrToe
                    detectVictory(cnvs)
                }
            } else{
                botGame(cnvs)
            }
        }
    }
}