# Bhada Chitthi - Application Flow Diagram

## First Launch Flow

```
┌─────────────────────────────────────────────────────────────┐
│                     User Opens App                          │
│                    (First Time)                             │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                   MainActivity                               │
│  • onCreate() called                                         │
│  • ProfileManager checks if profile exists                  │
│  • Profile not found                                         │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│              Redirect to OnboardingActivity                  │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│              STEP 1: Mobile Number                           │
│  ┌──────────────────────────────────────────────────┐       │
│  │ Enter 10-digit mobile number                     │       │
│  │ ┌──────────────────────────────────────────────┐ │       │
│  │ │ [9876543210]                                 │ │       │
│  │ └──────────────────────────────────────────────┘ │       │
│  │                                                  │       │
│  │ Validation:                                      │       │
│  │ ✓ Must be exactly 10 digits                     │       │
│  │ ✓ Must be numeric only                          │       │
│  │ ✓ Cannot be empty                               │       │
│  │                                                  │       │
│  │                              [Back]   [Next] ─┐  │       │
│  └──────────────────────────────────────────────┼──┘       │
└─────────────────────────────────────────────────┼──────────┘
                                                   │
                                                   ▼
┌─────────────────────────────────────────────────────────────┐
│              STEP 2: Name                                    │
│  ┌──────────────────────────────────────────────────┐       │
│  │ Enter your name                                  │       │
│  │ ┌──────────────────────────────────────────────┐ │       │
│  │ │ [John Doe]                                   │ │       │
│  │ └──────────────────────────────────────────────┘ │       │
│  │                                                  │       │
│  │ Validation:                                      │       │
│  │ ✓ Cannot be empty                               │       │
│  │                                                  │       │
│  │ Note: Can be edited max 1 time after creation   │       │
│  │                                                  │       │
│  │                              [Back]   [Next] ─┐  │       │
│  └──────────────────────────────────────────────┼──┘       │
└─────────────────────────────────────────────────┼──────────┘
                                                   │
                                                   ▼
┌─────────────────────────────────────────────────────────────┐
│              STEP 3: Business Name                           │
│  ┌──────────────────────────────────────────────────┐       │
│  │ Enter your business name                         │       │
│  │ ┌──────────────────────────────────────────────┐ │       │
│  │ │ [ABC Traders]                                │ │       │
│  │ └──────────────────────────────────────────────┘ │       │
│  │                                                  │       │
│  │ Validation:                                      │       │
│  │ ✓ Cannot be empty                               │       │
│  │                                                  │       │
│  │ Note: Can be edited max 1 time after creation   │       │
│  │                                                  │       │
│  │                              [Back]  [Finish] ─┐ │       │
│  └──────────────────────────────────────────────┼──┘       │
└─────────────────────────────────────────────────┼──────────┘
                                                   │
                                                   ▼
┌─────────────────────────────────────────────────────────────┐
│              Save Profile to SharedPreferences               │
│  • mobile_number: "9876543210"                              │
│  • name: "John Doe"                                         │
│  • business_name: "ABC Traders"                             │
│  • profile_created: true                                    │
│  • name_edit_count: 0                                       │
│  • business_name_edit_count: 0                              │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│              Redirect to MainActivity                        │
│  ┌──────────────────────────────────────────────────┐       │
│  │     Welcome to Bhada Chitthi                     │       │
│  │                                                  │       │
│  │     Mobile Number: 9876543210                   │       │
│  │     Name: John Doe                              │       │
│  │     Business Name: ABC Traders                  │       │
│  │                                                  │       │
│  │                    [Edit Profile]               │       │
│  └──────────────────────────────────────────────────┘       │
└─────────────────────────────────────────────────────────────┘
```

## Subsequent Launch Flow

```
┌─────────────────────────────────────────────────────────────┐
│                  User Opens App                              │
│               (Profile Exists)                               │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                   MainActivity                               │
│  • onCreate() called                                         │
│  • ProfileManager checks if profile exists                  │
│  • Profile found ✓                                          │
│  • Load and display profile                                 │
└─────────────────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│              Profile Display Screen                          │
│  ┌──────────────────────────────────────────────────┐       │
│  │     Welcome to Bhada Chitthi                     │       │
│  │                                                  │       │
│  │     Mobile Number: 9876543210                   │       │
│  │     Name: John Doe                              │       │
│  │     Business Name: ABC Traders                  │       │
│  │                                                  │       │
│  │                    [Edit Profile] ────┐         │       │
│  └───────────────────────────────────────┼─────────┘       │
└────────────────────────────────────────────┼───────────────┘
                                             │
                                             ▼
                          ┌──────────────────────────────────┐
                          │    Edit Profile Dialog           │
                          │  ┌────────────────────────────┐  │
                          │  │ • Edit Name                │  │
                          │  │ • Edit Business Name       │  │
                          │  └────────────────────────────┘  │
                          └──────────────────────────────────┘
```

## Edit Flow (Name Example)

```
┌─────────────────────────────────────────────────────────────┐
│              User Clicks "Edit Profile"                      │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│           Check Edit Permissions                             │
│  • canEditName() → Returns true if edit_count < 1           │
│  • canEditBusinessName() → Returns true if edit_count < 1   │
│  • Mobile number → Never editable                           │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│           Show Available Options Dialog                      │
│  ┌──────────────────────────────────────────────────┐       │
│  │  Edit Profile                                    │       │
│  │  ┌────────────────────────────────────────────┐  │       │
│  │  │  [✓] Edit Name                             │  │       │
│  │  │  [✓] Edit Business Name                    │  │       │
│  │  └────────────────────────────────────────────┘  │       │
│  └──────────────────────────────────────────────────┘       │
└─────────────────────┬───────────────────────────────────────┘
                      │ User selects "Edit Name"
                      ▼
┌─────────────────────────────────────────────────────────────┐
│           Edit Name Dialog                                   │
│  ┌──────────────────────────────────────────────────┐       │
│  │  Edit Name                                       │       │
│  │  ┌────────────────────────────────────────────┐  │       │
│  │  │ [John Smith]  ← Updated                    │  │       │
│  │  └────────────────────────────────────────────┘  │       │
│  │                                                  │       │
│  │                      [Cancel]  [Save] ──┐       │       │
│  └─────────────────────────────────────────┼───────┘       │
└────────────────────────────────────────────┼───────────────┘
                                             │
                                             ▼
┌─────────────────────────────────────────────────────────────┐
│           Update Profile                                     │
│  • Update name in SharedPreferences                         │
│  • Increment name_edit_count (0 → 1)                        │
│  • Show success toast                                       │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│           Refresh Profile Display                            │
│  ┌──────────────────────────────────────────────────┐       │
│  │     Welcome to Bhada Chitthi                     │       │
│  │                                                  │       │
│  │     Mobile Number: 9876543210                   │       │
│  │     Name: John Smith  ← Updated                 │       │
│  │     Business Name: ABC Traders                  │       │
│  │                                                  │       │
│  │                    [Edit Profile]               │       │
│  └──────────────────────────────────────────────────┘       │
└─────────────────────────────────────────────────────────────┘
```

## Edit Limit Reached Flow

```
┌─────────────────────────────────────────────────────────────┐
│         User Clicks "Edit Profile" (2nd time)                │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│           Check Edit Permissions                             │
│  • canEditName() → Returns FALSE (edit_count = 1)           │
│  • canEditBusinessName() → Returns true (edit_count = 0)    │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│           Show Available Options Dialog                      │
│  ┌──────────────────────────────────────────────────┐       │
│  │  Edit Profile                                    │       │
│  │  ┌────────────────────────────────────────────┐  │       │
│  │  │  [✓] Edit Business Name                    │  │       │
│  │  │  (Name not shown - limit reached)          │  │       │
│  │  └────────────────────────────────────────────┘  │       │
│  └──────────────────────────────────────────────────┘       │
└─────────────────────────────────────────────────────────────┘
```

## Data Storage Structure (SharedPreferences)

```
BhadaChitthiPrefs
├── mobile_number: "9876543210"
├── name: "John Smith"
├── business_name: "ABC Traders"
├── profile_created: true
├── name_edit_count: 1
└── business_name_edit_count: 0
```

## Key Components Interaction

```
┌──────────────────┐
│   MainActivity   │
└────────┬─────────┘
         │ Uses
         ▼
┌──────────────────┐       Stores/Retrieves      ┌─────────────────────┐
│ ProfileManager   │◄──────────────────────────►│  SharedPreferences  │
└────────┬─────────┘                             └─────────────────────┘
         │ Manages
         ▼
┌──────────────────┐
│   UserProfile    │
│  (Data Class)    │
└──────────────────┘

┌──────────────────────┐
│ OnboardingActivity   │
└────────┬─────────────┘
         │ Uses
         ▼
┌──────────────────┐
│ ProfileManager   │
└──────────────────┘
```

## Complete Feature Matrix

| Feature | Implementation | Status |
|---------|----------------|--------|
| App Name "Bhada Chitthi" | strings.xml | ✅ |
| First Launch Onboarding | MainActivity redirect | ✅ |
| 3-Step Form | OnboardingActivity | ✅ |
| Mobile Number (10 digits) | Regex validation | ✅ |
| Mobile Number (Numeric) | inputType="number" | ✅ |
| Mobile Number (Mandatory) | Required validation | ✅ |
| Mobile Number (Immutable) | Not in edit options | ✅ |
| Name (Mandatory) | Required validation | ✅ |
| Name (Editable) | Edit dialog | ✅ |
| Name (Max 1 Edit) | Edit count tracking | ✅ |
| Business Name (Mandatory) | Required validation | ✅ |
| Business Name (Editable) | Edit dialog | ✅ |
| Business Name (Max 1 Edit) | Edit count tracking | ✅ |
| Data Persistence | SharedPreferences | ✅ |
| Material Design UI | Material Components | ✅ |
