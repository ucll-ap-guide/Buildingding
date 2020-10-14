package buildingding.proximus.view

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import buildingding.proximus.R
import buildingding.proximus.model.Dijkstra
import buildingding.proximus.model.Graph
import buildingding.proximus.model.Location
import buildingding.proximus.repository.LocationRepository
import buildingding.proximus.repository.TextDirections

class NavigationTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_text)
        setTitle(R.string.title_text_navigation)
        val linearLayoutLocations: LinearLayout = findViewById(R.id.scrollViewLayout)
        val wholeRoute = calculatePath(intent.getStringExtra("startPosition"), intent.getStringExtra("endPosition"))
        var filteredRoute = wholeRoute?.filter { LocationRepository.getRealLocationsNames().contains(it) }
                ?.forEach {
                    val textView = TextView(this)
                    textView.text = TextDirections.getNextDirection(it)
                    textView.textSize = 24f
                    textView.gravity = Gravity.CENTER
                    textView.setBackgroundColor(getColor(R.color.colorWhite))
                    textView.setTextColor(getColor(R.color.colorUcllDarkBlue))
                    textView.setPadding(0, 24, 0, 24)
                    linearLayoutLocations.addView(textView)
                }
    }

    private fun calculatePath(startLocation: String, endLocation: String): List<String?>? {
        val nodes = LocationRepository.locations.associateBy({ it.id }, { it })
        val graph = Graph(nodes as HashMap<Int, Location>)
        val dijkstra = Dijkstra(graph.getPriorityQueue())
        /*
        println("\n All paths: \n")
        println(locationRepository.getLocationByName("C004")?.id?.let { dijkstra.calculatePaths(it, graph.nodes) })
        */
        val start = LocationRepository.getLocationByName(startLocation)?.id
        val end = LocationRepository.getLocationByName(endLocation)?.id
        return start?.let<Int, List<String?>?> { end?.let { it1 -> dijkstra.getPath(it, it1, nodes) } }
    }
}