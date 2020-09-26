package buildingding.proximus.model

abstract class Location(val name: String, val neighbours: List<Location>, val floor: Floor) {
    fun getNeighbour(name: String): Location? {
        return neighbours.find { neighbour -> neighbour.name === name }
    }
}