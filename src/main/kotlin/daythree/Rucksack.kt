package daythree

class Rucksack(private val compartments: List<Compartment>) {
    fun getNthCompartment(n: Int): Compartment {
        return compartments[n]
    }

    fun getContents(): String {
        return compartments.joinToString { compartment -> compartment.contents }
    }
}