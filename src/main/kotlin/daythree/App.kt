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

        val intersections = rucksacks.flatMap { rucksack -> rucksack.getNthCompartment(0).getContentIntersection(rucksack.getNthCompartment(1)) }

        val score = intersections.map{ intersection -> items.indexOf(intersection) + 1 }.sum()

        println("Part one answer: $score")
    }


}