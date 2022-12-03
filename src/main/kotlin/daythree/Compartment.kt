package daythree

class Compartment(val contents: String) {
    fun getContentIntersection(otherCompartment: Compartment): Set<Char> {
        return contents.toSet().intersect(otherCompartment.contents.toSet())
    }
}
