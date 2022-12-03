package daythree

class Compartment(val contents: String) {
    fun getContentIntersection(otherCompartment: Compartment): List<Char> {
        return contents.toCharArray().intersect(otherCompartment.contents.toCharArray().toList().toSet()).toList()
    }
}
