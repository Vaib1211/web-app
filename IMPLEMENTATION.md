# Bhada Chitthi - Implementation Details

## Overview

Bhada Chitthi is an Android application that provides a step-by-step onboarding process for creating a business profile on first launch.

## Key Features Implemented

### 1. First-Time User Onboarding
- When the app is launched for the first time, users are automatically redirected to the onboarding flow
- The onboarding consists of 3 steps presented one at a time
- Users can navigate back and forth between steps
- All data is validated before proceeding to the next step

### 2. Profile Fields

#### Mobile Number (Step 1)
- **Type**: Numeric input
- **Length**: Exactly 10 digits
- **Validation**: Must be numeric and exactly 10 digits
- **Mandatory**: Yes
- **Editable**: No (cannot be changed after creation)

#### Name (Step 2)
- **Type**: Text input
- **Validation**: Required field
- **Mandatory**: Yes
- **Editable**: Yes, maximum 1 time after initial creation

#### Business Name (Step 3)
- **Type**: Text input
- **Validation**: Required field
- **Mandatory**: Yes
- **Editable**: Yes, maximum 1 time after initial creation

### 3. Edit Restrictions
The ProfileManager class tracks the number of edits for Name and Business Name:
- Maximum 1 edit allowed for Name field
- Maximum 1 edit allowed for Business Name field
- Mobile number cannot be edited after creation
- Edit dialogs only show fields that can still be edited

### 4. Data Persistence
- Profile data is stored using Android SharedPreferences
- Edit counts are tracked to enforce edit restrictions
- Data persists across app sessions

## Application Flow

### First Launch
1. User opens the app
2. MainActivity checks if profile exists
3. If no profile exists, user is redirected to OnboardingActivity
4. User completes 3-step form:
   - Step 1: Enter mobile number
   - Step 2: Enter name
   - Step 3: Enter business name
5. Profile is saved to SharedPreferences
6. User is redirected to MainActivity

### Subsequent Launches
1. User opens the app
2. MainActivity loads and displays profile information
3. User can tap "Edit Profile" button
4. Dialog shows available fields that can be edited
5. User can edit allowed fields
6. Changes are saved and edit count is incremented

## Technical Implementation

### Architecture Components

1. **MainActivity.kt**
   - Entry point of the application
   - Checks if profile exists on launch
   - Displays profile information
   - Provides edit functionality with restrictions

2. **OnboardingActivity.kt**
   - Step-by-step form implementation
   - Dynamic view inflation for each step
   - Input validation
   - Navigation between steps

3. **ProfileManager.kt**
   - Handles all SharedPreferences operations
   - Tracks edit counts
   - Enforces edit restrictions
   - Provides profile CRUD operations

4. **UserProfile.kt**
   - Data class representing user profile
   - Contains mobile number, name, and business name

### Layouts

- **activity_main.xml**: Main screen layout showing profile details
- **activity_onboarding.xml**: Container for step-by-step onboarding
- **step_mobile_number.xml**: Mobile number input step
- **step_name.xml**: Name input step
- **step_business_name.xml**: Business name input step

### Validation Rules

1. **Mobile Number**:
   ```kotlin
   fun isValidMobileNumber(number: String): Boolean {
       return number.matches(Regex("^[0-9]{10}$"))
   }
   ```

2. **Required Fields**:
   - All fields must have non-empty values
   - Whitespace is trimmed before validation

3. **Edit Limits**:
   - Tracked in SharedPreferences with edit count keys
   - Maximum of 1 edit per field (name and business name)

## Testing the Application

To test the application:

1. **First Launch Test**:
   - Launch app → Should show onboarding
   - Complete all 3 steps → Should save profile
   - Should redirect to main screen

2. **Validation Test**:
   - Try entering invalid mobile number (not 10 digits) → Should show error
   - Try leaving fields empty → Should show error
   - Enter valid data → Should proceed

3. **Edit Restriction Test**:
   - Edit name once → Should succeed
   - Try editing name again → Should show limit reached message
   - Same for business name

4. **Persistence Test**:
   - Create profile and close app
   - Reopen app → Should show profile on main screen
   - Profile data should persist

## Future Enhancements

Potential features for future versions:
- Profile photo upload
- Multiple business profiles
- Export/import profile data
- Cloud backup
- Profile verification via OTP
