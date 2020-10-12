package buildingding.proximus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import buildingding.proximus.R
import buildingding.proximus.model.Location
import buildingding.proximus.repository.LocationRepository

class ChooseLocationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_locations)
        populateLocationList()
    }

    private fun populateLocationList(){
        val chosenStartLocation: String? = if (intent.hasExtra("startPosition"))  intent.getStringExtra("startPosition") else ""
        val chosenEndLocation: String? = if (intent.hasExtra("endPosition")) intent.getStringExtra("endPosition") else ""
        val target: String? = intent.getStringExtra("target")
        val linearLayoutLocations:LinearLayout = findViewById(R.id.scrollViewLayout)

        LocationRepository.locations.forEach {
            val textView = TextView(this)
            val locationName = it.name
            textView.text = locationName
            textView.textSize = 24f
            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(getColor(R.color.colorWhite))
            textView.setTextColor(getColor(R.color.colorUcllDarkBlue))
            textView.setPadding(0,24,0,24)
            textView.setOnClickListener {
                val intent = Intent(this@ChooseLocationsActivity, NavigationActivity::class.java)
                if (chosenStartLocation!!.isNotEmpty()) intent.putExtra("startPosition", chosenStartLocation)
                if (chosenEndLocation!!.isNotEmpty()) intent.putExtra("endPosition", chosenEndLocation)
                intent.putExtra(target, locationName)
                startActivity(intent)
            }
            linearLayoutLocations.addView(textView)
        }
    }



}