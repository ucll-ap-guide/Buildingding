package buildingding.proximus.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import buildingding.proximus.R
import buildingding.proximus.repository.SettingsRepository


class SettingsActivity : AppCompatActivity() {

    private var originalLanguage = SettingsRepository.language.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        populateSettings()
        setTitle(R.string.title_settings)
    }

    override fun onResume() {
        super.onResume()
        val oldLanguage = originalLanguage
        val newLanguage = SettingsRepository.language.name
        if (oldLanguage != newLanguage) {
            finish()
            startActivity(intent)
        }
    }

    private fun populateSettings() {
        val linearLayoutLocations: LinearLayout = findViewById(R.id.scrollViewLayout)
        linearLayoutLocations.addView(createLanguage())
        linearLayoutLocations.addView(createStartChoice())
    }

    private fun createLanguage(): TextView {
        val textView = TextView(this)
        textView.text = getString(R.string.settings_option_language)
        textView.textSize = 24f
        textView.gravity = Gravity.CENTER
        textView.setBackgroundColor(getColor(R.color.colorWhite))
        textView.setTextColor(getColor(R.color.colorUcllDarkBlue))
        textView.setPadding(0, 24, 0, 24)
        textView.setOnClickListener {
            val intent = Intent(this@SettingsActivity, ChooseLanguageActivity::class.java)
            startActivity(intent)
        }
        return textView
    }

    private fun createStartChoice(): TextView {
        val textView = TextView(this)
        textView.text = getString(R.string.settings_option_start_choice)
        textView.textSize = 24f
        textView.gravity = Gravity.CENTER
        textView.setBackgroundColor(getColor(R.color.colorWhite))
        textView.setTextColor(getColor(R.color.colorUcllDarkBlue))
        textView.setPadding(0, 24, 0, 24)
        textView.setOnClickListener {
            val intent = Intent(this@SettingsActivity, ChooseStartChoice::class.java)
            startActivity(intent)
        }
        return textView
    }
}