plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.sap.rhythmhaven"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sap.rhythmhaven"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit and related dependencies
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Corrected Retrofit version
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // JSON parsing with Gson
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11") // Latest logging interceptor
}
