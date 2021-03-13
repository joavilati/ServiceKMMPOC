plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    val workVersion = "2.5.0"
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation ("androidx.work:work-runtime-ktx:$workVersion")
}

android {
    buildFeatures {
        viewBinding = true
    }
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.example.servicepoc.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}