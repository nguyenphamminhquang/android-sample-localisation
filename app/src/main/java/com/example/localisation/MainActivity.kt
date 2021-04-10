package com.example.localisation

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val KEY_LANGUAGE = "Language"
    private val KEY_COUNTRY = "COUNTRY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val languageCode = intent.extras?.getString(KEY_LANGUAGE)
        val countryCode = intent.extras?.getString(KEY_COUNTRY)
        if (!languageCode.isNullOrEmpty() && !countryCode.isNullOrEmpty()) setLocale(this, languageCode, countryCode)
        setContentView(R.layout.activity_main)
        setupLanguageSelection()
    }

    private fun setupLanguageSelection(){
        ArrayAdapter.createFromResource(
            this,
            R.array.languages_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerLanguage.adapter = adapter
        }
        spinnerLanguage.onItemSelectedListener = this
    }

    fun setLocale(activity: Activity, languageCode: String, countryCode: String) {
        val locale = Locale(languageCode, countryCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    fun changeLanguage(languageCode: String, countryCode: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(KEY_LANGUAGE, languageCode)
        intent.putExtra(KEY_COUNTRY, countryCode)
        startActivity(intent)
        finish()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            1 -> {
                changeLanguage( "en", "")
            }
            2 -> {
                changeLanguage( "en", "AU")
            }
            3 -> {
                changeLanguage( "en", "CN")
            }
            4 -> {
                changeLanguage( "en", "CA")
            }
            5 -> {
                changeLanguage( "en", "MY")
            }
            6 -> {
                changeLanguage( "en", "MM")
            }
        }
    }
}
