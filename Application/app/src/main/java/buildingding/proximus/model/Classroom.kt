package buildingding.proximus.model

class Classroom(name: String, neighbours: List<Location>, floor: Floor) :
    Location(name, neighbours, floor) {
}