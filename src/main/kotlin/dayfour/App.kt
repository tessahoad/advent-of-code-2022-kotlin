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

        println("Part One solution: ${partOne(pairedAssignments)}")
        println("Part Two solution: ${partTwo(pairedAssignments)}")

    }

    private fun partOne(assignmentPairs: List<List<Assignment>>): Int {
        return assignmentPairs.fold(0) { totalContainments, assignmentPair ->
            val firstAssignment = assignmentPair[0]
            val secondAssignment = assignmentPair[1]
            if (firstAssignment.contains(secondAssignment) || secondAssignment.contains(firstAssignment)) totalContainments + 1 else totalContainments
        }
    }

    private fun partTwo(assignmentPairs: List<List<Assignment>>): Int {
        return assignmentPairs.fold(0) { totalOverlaps, assignmentPair ->
            val firstAssignment = assignmentPair[0]
            val secondAssignment = assignmentPair[1]
            if (firstAssignment.overlaps(secondAssignment) || secondAssignment.overlaps(firstAssignment)) totalOverlaps + 1 else totalOverlaps
        }
    }
}

class Assignment(private val start: Int, private val end: Int) {
    fun contains(otherAssignment: Assignment): Boolean {
        val startInRange = otherAssignment.start in start..end
        val endInRange = otherAssignment.end in start..end
        return startInRange && endInRange
    }

    fun overlaps(otherAssignment: Assignment): Boolean {
        val startInRange = otherAssignment.start in start..end
        val endInRange = otherAssignment.end in start..end
        return startInRange || endInRange
    }
}