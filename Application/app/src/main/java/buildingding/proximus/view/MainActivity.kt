package buildingding.proximus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import buildingding.proximus.R
import buildingding.proximus.model.Floor
import buildingding.proximus.model.Location
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rows = getLocationsAsFromCvsFile("locations.csv")
        val spinner_start = findViewById<Spinner>(R.id.spinner_start)
        val spinner_end = findViewById<Spinner>(R.id.spinner_end)
        if (spinner_start != null && spinner_end != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, rows
            )
            spinner_start.adapter = adapter
            spinner_end.adapter = adapter
        }
    }

    private fun getLocationsAsFromCvsFile(file: String): List<Location> {
        val inputStream = application.assets.open(file)
        val list = csvReader().readAll(inputStream)
        var locationMutableList = mutableListOf<Location>()
        list.forEach { row -> locationMutableList.add(Location(row.get(0), Floor.valueOf(row.get(1)))) }
        return  locationMutableList.toList()
    }

}