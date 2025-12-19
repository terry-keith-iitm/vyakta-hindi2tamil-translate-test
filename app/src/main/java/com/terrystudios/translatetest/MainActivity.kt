package com.terrystudios.translatetest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: EditText
    private lateinit var outputText: TextView
    private lateinit var translateButton: Button
    private lateinit var statusText: TextView
    
    private var progressJob: Job? = null

    private val translator by lazy {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.HINDI)
            .setTargetLanguage(TranslateLanguage.TAMIL)
            .build()
        Translation.getClient(options)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        inputText = findViewById(R.id.inputText)
        outputText = findViewById(R.id.outputText)
        translateButton = findViewById(R.id.translateButton)
        statusText = findViewById(R.id.statusText)

        // Add lifecycle observer to close translator when activity is destroyed
        lifecycle.addObserver(translator)

        // Download model
        downloadModel()

        // Set up translate button
        translateButton.setOnClickListener {
            translate()
        }
    }

    private fun downloadModel() {
        translateButton.isEnabled = false
        startDotProgress("Downloading model")

        val conditions = DownloadConditions.Builder()
            .build()

        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                stopDotProgress()
                statusText.text = "Model ready ✓"
                translateButton.isEnabled = true
            }
            .addOnFailureListener { exception ->
                stopDotProgress()
                statusText.text = "Download failed: ${exception.message}"
            }
    }

    private fun startDotProgress(message: String) {
        progressJob?.cancel()
        progressJob = lifecycleScope.launch {
            val dots = listOf(".", "..", "...")
            var index = 0
            while (true) {
                statusText.text = "$message${dots[index]}"
                index = (index + 1) % dots.size
                delay(400)
            }
        }
    }

    private fun stopDotProgress() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun translate() {
        val text = inputText.text.toString().trim()
        if (text.isEmpty()) {
            outputText.text = ""
            return
        }

        statusText.text = "Translating..."
        translateButton.isEnabled = false

        translator.translate(text)
            .addOnSuccessListener { translatedText ->
                outputText.text = translatedText
                statusText.text = "Model ready ✓"
                translateButton.isEnabled = true
            }
            .addOnFailureListener { exception ->
                outputText.text = "Error: ${exception.message}"
                statusText.text = "Translation failed"
                translateButton.isEnabled = true
            }
    }
}