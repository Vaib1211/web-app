package com.bhadachitthi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    
    private lateinit var profileManager: ProfileManager
    private lateinit var profileDetailsText: TextView
    private lateinit var editProfileButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        profileManager = ProfileManager(this)
        
        // Check if profile exists, if not redirect to onboarding
        if (!profileManager.isProfileCreated()) {
            startOnboarding()
            return
        }
        
        setContentView(R.layout.activity_main)
        
        profileDetailsText = findViewById(R.id.profileDetailsText)
        editProfileButton = findViewById(R.id.editProfileButton)
        
        displayProfile()
        
        editProfileButton.setOnClickListener {
            showEditProfileDialog()
        }
    }
    
    private fun startOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }
    
    private fun displayProfile() {
        val profile = profileManager.getProfile()
        if (profile != null) {
            val details = """
                Mobile Number: ${profile.mobileNumber}
                Name: ${profile.name}
                Business Name: ${profile.businessName}
            """.trimIndent()
            profileDetailsText.text = details
        }
    }
    
    private fun showEditProfileDialog() {
        val profile = profileManager.getProfile() ?: return
        
        val options = mutableListOf<String>()
        val canEditName = profileManager.canEditName()
        val canEditBusinessName = profileManager.canEditBusinessName()
        
        if (canEditName) {
            options.add("Edit Name")
        }
        if (canEditBusinessName) {
            options.add("Edit Business Name")
        }
        
        if (options.isEmpty()) {
            Toast.makeText(this, "All fields have reached their edit limit", Toast.LENGTH_SHORT).show()
            return
        }
        
        AlertDialog.Builder(this)
            .setTitle("Edit Profile")
            .setItems(options.toTypedArray()) { _, which ->
                val selectedOption = options[which]
                if (selectedOption == "Edit Name" && canEditName) {
                    showEditNameDialog(profile.name)
                } else if (selectedOption == "Edit Business Name" && canEditBusinessName) {
                    showEditBusinessNameDialog(profile.businessName)
                }
            }
            .show()
    }
    
    private fun showEditNameDialog(currentName: String) {
        val editText = TextInputEditText(this)
        editText.setText(currentName)
        editText.setPadding(50, 30, 50, 30)
        
        AlertDialog.Builder(this)
            .setTitle("Edit Name")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    if (profileManager.updateName(newName)) {
                        Toast.makeText(this, "Name updated successfully", Toast.LENGTH_SHORT).show()
                        displayProfile()
                    } else {
                        Toast.makeText(this, getString(R.string.edit_limit_reached), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showEditBusinessNameDialog(currentBusinessName: String) {
        val editText = TextInputEditText(this)
        editText.setText(currentBusinessName)
        editText.setPadding(50, 30, 50, 30)
        
        AlertDialog.Builder(this)
            .setTitle("Edit Business Name")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newBusinessName = editText.text.toString().trim()
                if (newBusinessName.isNotEmpty()) {
                    if (profileManager.updateBusinessName(newBusinessName)) {
                        Toast.makeText(this, "Business name updated successfully", Toast.LENGTH_SHORT).show()
                        displayProfile()
                    } else {
                        Toast.makeText(this, getString(R.string.edit_limit_reached), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
