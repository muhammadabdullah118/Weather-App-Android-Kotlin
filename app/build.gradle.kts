plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherapp"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //lotie animation
    implementation ("com.airbnb.android:lottie:6.0.0")
    //view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    //Retrofit

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //httpClient
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

    //logging interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // androidx Activity Liberary for getting viewmodel in liberary
    implementation ("androidx.activity:activity-ktx:1.9.1")


// For coroutine support (optional, but recommended)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}