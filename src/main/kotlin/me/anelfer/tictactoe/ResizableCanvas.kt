package me.anelfer.tictactoe

import javafx.beans.Observable
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

class ResizableCanvas : Canvas() {
    var left: Double = 0.0
    var right: Double = 0.0
    var top: Double = 0.0
    var bottom: Double = 0.0
    var sqLen: Double = 0.0

    private fun draw() {
        val width = width
        val height = height
        val gc = graphicsContext2D

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

//            //center line
//            gc.stroke = Color.RED
//            gc.strokeLine(width/2, 0.0, width/2, height)
//            gc.strokeLine(0.0, height/2, width, height/2)


    }

    init {
        widthProperty().addListener { evt: Observable? -> draw() }
        heightProperty().addListener { evt: Observable? -> draw() }
    }
}