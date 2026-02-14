package com.bhadachitthi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class OnboardingActivity : AppCompatActivity() {
    
    private lateinit var profileManager: ProfileManager
    private var currentStep = 1
    private val totalSteps = 3
    
    private var mobileNumber = ""
    private var name = ""
    private var businessName = ""
    
    private lateinit var stepIndicator: TextView
    private lateinit var formContainer: android.widget.FrameLayout
    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        
        profileManager = ProfileManager(this)
        
        stepIndicator = findViewById(R.id.stepIndicator)
        formContainer = findViewById(R.id.formContainer)
        backButton = findViewById(R.id.backButton)
        nextButton = findViewById(R.id.nextButton)
        
        backButton.setOnClickListener { goToPreviousStep() }
        nextButton.setOnClickListener { goToNextStep() }
        
        showStep(currentStep)
    }
    
    private fun showStep(step: Int) {
        currentStep = step
        stepIndicator.text = getString(R.string.step, step)
        
        formContainer.removeAllViews()
        
        when (step) {
            1 -> showMobileNumberStep()
            2 -> showNameStep()
            3 -> showBusinessNameStep()
        }
        
        backButton.visibility = if (step == 1) View.GONE else View.VISIBLE
        nextButton.text = if (step == totalSteps) getString(R.string.finish) else getString(R.string.next)
    }
    
    private fun showMobileNumberStep() {
        val view = LayoutInflater.from(this).inflate(R.layout.step_mobile_number, formContainer, false)
        val editText = view.findViewById<TextInputEditText>(R.id.mobileNumberEditText)
        val errorText = view.findViewById<TextView>(R.id.errorText)
        
        editText.setText(mobileNumber)
        
        nextButton.setOnClickListener {
            val input = editText.text.toString().trim()
            errorText.visibility = View.GONE
            
            if (input.isEmpty()) {
                errorText.text = getString(R.string.error_required_field)
                errorText.visibility = View.VISIBLE
                return@setOnClickListener
            }
            
            if (!isValidMobileNumber(input)) {
                errorText.text = getString(R.string.error_invalid_mobile)
                errorText.visibility = View.VISIBLE
                return@setOnClickListener
            }
            
            mobileNumber = input
            showStep(currentStep + 1)
        }
        
        formContainer.addView(view)
    }
    
    private fun showNameStep() {
        val view = LayoutInflater.from(this).inflate(R.layout.step_name, formContainer, false)
        val editText = view.findViewById<TextInputEditText>(R.id.nameEditText)
        val errorText = view.findViewById<TextView>(R.id.errorText)
        
        editText.setText(name)
        
        nextButton.setOnClickListener {
            val input = editText.text.toString().trim()
            errorText.visibility = View.GONE
            
            if (input.isEmpty()) {
                errorText.text = getString(R.string.error_required_field)
                errorText.visibility = View.VISIBLE
                return@setOnClickListener
            }
            
            name = input
            showStep(currentStep + 1)
        }
        
        formContainer.addView(view)
    }
    
    private fun showBusinessNameStep() {
        val view = LayoutInflater.from(this).inflate(R.layout.step_business_name, formContainer, false)
        val editText = view.findViewById<TextInputEditText>(R.id.businessNameEditText)
        val errorText = view.findViewById<TextView>(R.id.errorText)
        
        editText.setText(businessName)
        
        nextButton.setOnClickListener {
            val input = editText.text.toString().trim()
            errorText.visibility = View.GONE
            
            if (input.isEmpty()) {
                errorText.text = getString(R.string.error_required_field)
                errorText.visibility = View.VISIBLE
                return@setOnClickListener
            }
            
            businessName = input
            saveProfileAndFinish()
        }
        
        formContainer.addView(view)
    }
    
    private fun goToPreviousStep() {
        if (currentStep > 1) {
            showStep(currentStep - 1)
        }
    }
    
    private fun goToNextStep() {
        // This is handled by individual step click listeners
    }
    
    private fun isValidMobileNumber(number: String): Boolean {
        return number.matches(Regex("^[0-9]{10}$"))
    }
    
    private fun saveProfileAndFinish() {
        val profile = UserProfile(mobileNumber, name, businessName)
        profileManager.saveProfile(profile)
        
        Toast.makeText(this, getString(R.string.profile_saved), Toast.LENGTH_SHORT).show()
        
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
