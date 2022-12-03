package daythree

object App {

    private val items = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    @JvmStatic
    fun main(args: Array<String>) {
        val fileContent = App::class.java.getResource("/daythree/input.txt").readText()

        val rucksacks = fileContent.split("\n").map{ rucksackContents ->
            val contentMidPoint = rucksackContents.length / 2
            val firstCompartmentContent = rucksackContents.substring(0, contentMidPoint)
            val secondCompartmentContent = rucksackContents.substring(contentMidPoint, rucksackContents.length)
            Rucksack(listOf(Compartment(firstCompartmentContent), Compartment(secondCompartmentContent)))
        }

        println("Part one answer: ${partOne(rucksacks)}")
        println("Part two answer: ${partTwo(rucksacks)}")
    }

    private fun partOne(rucksacks: List<Rucksack>): Int {
        val intersections = rucksacks.flatMap { rucksack ->
            rucksack.getNthCompartment(0).getContentIntersection(rucksack.getNthCompartment(1))
        }

        return intersections.map { intersection -> items.indexOf(intersection) + 1 }.sum()
    }

    private fun partTwo(rucksacks: List<Rucksack>): Int {
        val groupedRucksacks = rucksacks.chunked(3)
        val groupedContents = groupedRucksacks.map { rs -> rs.map { rucksack -> rucksack.getContents() } }
        val commonItems = groupedContents.flatMap { gc ->
            gc.fold(items.toSet()) { intersection, content ->
                intersection.intersect(content.toSet())
            }
        }
        return commonItems.map { item -> items.indexOf(item) + 1 }.sum()
    }
}