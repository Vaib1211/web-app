package com.bhadachitthi

import android.content.Context
import android.content.SharedPreferences

class ProfileManager(context: Context) {
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "BhadaChitthiPrefs"
        private const val KEY_MOBILE_NUMBER = "mobile_number"
        private const val KEY_NAME = "name"
        private const val KEY_BUSINESS_NAME = "business_name"
        private const val KEY_NAME_EDIT_COUNT = "name_edit_count"
        private const val KEY_BUSINESS_NAME_EDIT_COUNT = "business_name_edit_count"
        private const val KEY_PROFILE_CREATED = "profile_created"
        private const val MAX_EDIT_COUNT = 1
    }
    
    fun isProfileCreated(): Boolean {
        return sharedPreferences.getBoolean(KEY_PROFILE_CREATED, false)
    }
    
    fun saveProfile(profile: UserProfile) {
        sharedPreferences.edit().apply {
            putString(KEY_MOBILE_NUMBER, profile.mobileNumber)
            putString(KEY_NAME, profile.name)
            putString(KEY_BUSINESS_NAME, profile.businessName)
            putBoolean(KEY_PROFILE_CREATED, true)
            apply()
        }
    }
    
    fun getProfile(): UserProfile? {
        if (!isProfileCreated()) return null
        
        val mobileNumber = sharedPreferences.getString(KEY_MOBILE_NUMBER, "") ?: ""
        val name = sharedPreferences.getString(KEY_NAME, "") ?: ""
        val businessName = sharedPreferences.getString(KEY_BUSINESS_NAME, "") ?: ""
        
        return if (mobileNumber.isNotEmpty()) {
            UserProfile(mobileNumber, name, businessName)
        } else {
            null
        }
    }
    
    fun canEditName(): Boolean {
        return getNameEditCount() < MAX_EDIT_COUNT
    }
    
    fun canEditBusinessName(): Boolean {
        return getBusinessNameEditCount() < MAX_EDIT_COUNT
    }
    
    fun updateName(newName: String): Boolean {
        if (!canEditName()) return false
        
        sharedPreferences.edit().apply {
            putString(KEY_NAME, newName)
            putInt(KEY_NAME_EDIT_COUNT, getNameEditCount() + 1)
            apply()
        }
        return true
    }
    
    fun updateBusinessName(newBusinessName: String): Boolean {
        if (!canEditBusinessName()) return false
        
        sharedPreferences.edit().apply {
            putString(KEY_BUSINESS_NAME, newBusinessName)
            putInt(KEY_BUSINESS_NAME_EDIT_COUNT, getBusinessNameEditCount() + 1)
            apply()
        }
        return true
    }
    
    private fun getNameEditCount(): Int {
        return sharedPreferences.getInt(KEY_NAME_EDIT_COUNT, 0)
    }
    
    private fun getBusinessNameEditCount(): Int {
        return sharedPreferences.getInt(KEY_BUSINESS_NAME_EDIT_COUNT, 0)
    }
}
