package daytwo

import dayone.App

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val fileContent = App::class.java.getResource("/daytwo/input.txt").readText()

        // Part One
        val myShapes = fileContent.split("\n").map { newline ->
            val (_, xyzMapping) = newline.split(" ")
            Shape.fromXYZCHar(xyzMapping.toCharArray().first())
        }

        // Part Two
        val myResults = fileContent.split("\n").map { newline ->
            val (_, xyzMapping) = newline.split(" ")
            Result.fromXYZCHar(xyzMapping.toCharArray().first())
        }

        val elfShapes = fileContent.split("\n").map { newline ->
            val (abcMapping, _) = newline.split(" ")
            Shape.fromABCCHar(abcMapping.toCharArray().first())
        }

        println("Part One answer: ${partOneScore(elfShapes, myShapes)}")
        println("Part Two answer: ${partTwoScore(elfShapes, myResults)}")
    }

    private fun partOneScore(elfShapes: List<Shape>, myShapes: List<Shape>): Int {
        val results = myShapes.zip(elfShapes).map { (myShape, elfShape) -> myShape.beats(elfShape) }
        return results.map { result -> result.score }.sum() + myShapes.map { shape -> shape.score }.sum()
    }

    private fun partTwoScore(elfShapes: List<Shape>, myResults: List<Result>): Int {
        val myShapes = elfShapes.zip(myResults).map { (elfShape, myResult) ->
            Shape.values().first { shape -> shape.beats(elfShape) == myResult }
        }
        return myResults.map { result -> result.score }.sum() + myShapes.map { shape -> shape.score }.sum()
    }
}