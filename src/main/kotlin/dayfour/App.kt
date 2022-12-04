package dayfour

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val fileContent = App::class.java.getResource("/dayfour/input.txt").readText()

        val unparsedAssignments = fileContent.split("\n")

        val pairedAssignments = unparsedAssignments.map { unparsedAssignment ->
            val (firstAssignment, secondAssignment) = unparsedAssignment.split(",")
            val (firstStart, firstEnd) = firstAssignment.split("-")
            val (secondStart, secondEnd) = secondAssignment.split("-")

            listOf(Assignment(firstStart.toInt(), firstEnd.toInt()), Assignment(secondStart.toInt(), secondEnd.toInt()))
        }

        val fullyContainedAssignmentPairs = pairedAssignments.fold(0) { totalContainments, assignmentPair ->
            val firstAssignment = assignmentPair[0]
            val secondAssignment = assignmentPair[1]
            if (firstAssignment.contains(secondAssignment) || secondAssignment.contains(firstAssignment)) totalContainments + 1 else totalContainments
        }

        println("Part One solution: $fullyContainedAssignmentPairs")

    }

}

class Assignment(private val start: Int, private val end: Int) {
    fun contains(otherAssignment: Assignment): Boolean {
        return otherAssignment.start >= start && otherAssignment.end <= end
    }
}