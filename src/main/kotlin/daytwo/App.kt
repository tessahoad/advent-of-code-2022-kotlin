package daytwo

import dayone.App

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val fileContent = App::class.java.getResource("/daytwo/input.txt").readText()

        val myShapes = fileContent.split("\n").map { newline ->
            val (_, xyzMapping) = newline.split(" ")
            Shape.fromXYZCHar(xyzMapping.toCharArray().first())
        }

        val results = fileContent.split("\n").map { newline ->
            val (abcMapping, xyzMapping) = newline.split(" ")
            val elfShape = Shape.fromABCCHar(abcMapping.toCharArray().first())
            val myShape = Shape.fromXYZCHar(xyzMapping.toCharArray().first())
            myShape.beats(elfShape)
        }

        val score = results.map { result -> result.score }.sum() + myShapes.map { shape -> shape.score }.sum()

        println("Part One answer: $score")
    }
}