package me.anelfer.tictactoe

import javafx.beans.Observable
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

class ResizableCanvas(logicGame: LogicGame) : Canvas() {
    var left: Double = 0.0
    var right: Double = 0.0
    var top: Double = 0.0
    var bottom: Double = 0.0
    var sqLen: Double = 0.0

    val gc = graphicsContext2D
    val lg = logicGame

    fun draw() {
        val width = width
        val height = height
        gc.stroke = Color.BLACK
        gc.clearRect(0.0, 0.0, width, height)
        gc.lineWidth = 5.0

        if (width > height){
            val square = height

            sqLen = square / 3
            val ce = width / 2
            val a = ce - sqLen / 2
            val b = ce + sqLen / 2

            left = a - sqLen
            right = b + sqLen
            top = 0.0
            bottom = height

            //Vertical Line
            gc.strokeLine(left, 0.0, left, height)
            gc.strokeLine(a, 0.0, a, height)
            gc.strokeLine(b, 0.0, b, height)
            gc.strokeLine(right, 0.0, right, height)
            //Horizontal Line
            gc.strokeLine(left, 0.0, right , 0.0)
            gc.strokeLine(left, sqLen , right , sqLen)
            gc.strokeLine(left, 2.0/3 * square, right, 2.0/3 * square)
            gc.strokeLine(left, height, right , height)
        }
        else if(width < height){
            val square = width

            sqLen = square / 3
            val ce = height / 2
            val a = ce - sqLen / 2
            val b = ce + sqLen / 2

            val cew = width/2
            val lco = cew - sqLen / 2
            val lct = cew + sqLen / 2

            left = 0.0
            right = width
            top = a - sqLen
            bottom = b + sqLen

            //Vertical Line
            gc.strokeLine(0.0, a - sqLen, 0.0, b + sqLen)
            gc.strokeLine(lco, a - sqLen, lco, b + sqLen)
            gc.strokeLine(lct, a - sqLen, lct, b + sqLen)
            gc.strokeLine(width, a - sqLen, width, b + sqLen)

            //Horizontal Line
            gc.strokeLine(0.0, a - sqLen, b + sqLen , a - sqLen)
            gc.strokeLine(0.0, a , b + sqLen , a)
            gc.strokeLine(0.0, b, b + sqLen, b)
            gc.strokeLine(0.0, b + sqLen, width , b + sqLen)
        }
        else {
            val square = height

            left = 0.0
            right = width
            top = 0.0
            bottom = height
            sqLen = square/3

            gc.stroke = Color.ORANGE
            //Vertical Line
            gc.strokeLine(0.0, 0.0, 0.0, height)
            gc.strokeLine((width - (2.0 * sqLen)), 0.0, (width - (2.0 * sqLen)), height)
            gc.strokeLine((width - sqLen), 0.0, (width - sqLen), height)
            gc.strokeLine(width, 0.0, width, height)

            //Horizontal Line
            gc.strokeLine(0.0,0.0 , width , 0.0)
            gc.strokeLine(0.0,sqLen , width , sqLen)
            gc.strokeLine(0.0, 2.0/3 * square, width, 2.0/3 * square)
            gc.strokeLine(0.0, height, width, height)
        }

        drawFigure()
        if (lg.victoryFlag == 1){
            victoryLine(lg.victoryNumField)
        }
    }

    fun drawCross(clickX: Double, clickY: Double){
        gc.strokeLine(
            clickX - sqLen / 4,
            clickY - sqLen / 4,
            clickX + sqLen / 4,
            clickY + sqLen / 4
        )
        gc.strokeLine(
            clickX + sqLen / 4,
            clickY - sqLen / 4,
            clickX - sqLen / 4,
            clickY + sqLen / 4
        )
    }

    fun drawToe(clickX: Double, clickY: Double){
        gc.strokeOval(
            clickX - sqLen / 4,
            clickY - sqLen / 4,
            sqLen / 2,
            sqLen / 2)
    }

    fun drawFigure(){
        for (i in 1..9){
            if (lg.playerFieldMap[i] == -1){
                val row = (i + 2) / 3
                val column = (-3 * row) + 3 + i
                gc.strokeOval(
                    left + (sqLen * column) - 0.75 * sqLen ,
                    top + (sqLen * (row - 1)) + 0.25 * sqLen ,
                    sqLen / 2,
                    sqLen / 2
                )
            }else if (lg.playerFieldMap[i] == 1){
                val row = (i + 2) / 3
                val column = (-3 * row) + 3 + i

                gc.strokeLine(
                    left + (sqLen * column) - 0.75 * sqLen,
                    top + (sqLen * (row - 1)) + 0.25 * sqLen,
                    (left + (sqLen * column) - 0.75 * sqLen) + sqLen / 2,
                    (top + (sqLen * (row - 1)) + 0.25 * sqLen) + sqLen / 2
                )
                gc.strokeLine(
                    (left + (sqLen * column) - 0.75 * sqLen) + sqLen / 2,
                    top + (sqLen * (row - 1)) + 0.25 * sqLen,
                    left + (sqLen * column) - 0.75 * sqLen,
                    (top + (sqLen * (row - 1)) + 0.25 * sqLen) + sqLen / 2
                )

            }
        }
    }

    fun victoryLine(victoryNumField: MutableList<Int>){
        gc.stroke = Color.CORAL
        var rowF = 0
        var columnF = 0
        var columnS = 0
        var rowS = 0

        for (i in 0..2){
            println(victoryNumField[i])

            if (i == 0){
                rowF = (victoryNumField[i] + 2) / 3
                columnF = (-3 * rowF) + 3 + victoryNumField[i]
            } else if (i == 2){
                rowS = (victoryNumField[i] + 2) / 3
                columnS = (-3 * rowS) + 3 + victoryNumField[i]
            }
        }

        if (columnF == columnS){
            gc.strokeLine(
                left + ((sqLen * columnF) - (sqLen/2)) ,
                top,
                left + ((sqLen * columnF) - (sqLen/2)) ,
                bottom
            )
        } else if (rowF == rowS){
            gc.strokeLine(
                left,
                top + ((sqLen * rowF) - (sqLen/2)),
                right,
                top + ((sqLen * rowF) - (sqLen/2))
            )
        } else{
            if (columnF == 1){
                gc.strokeLine(
                    left + 2,
                    top + 2,
                    right - 2,
                    bottom - 2
                )
            } else if (columnF == 3){
                gc.strokeLine(
                    right - 2,
                    top + 2,
                    left + 2,
                    bottom - 2
                )
            }

        }
    }

    init {
        widthProperty().addListener { evt: Observable? -> draw() }
        heightProperty().addListener { evt: Observable? -> draw() }
    }
}