plugins {
    id("com.android.application")
}

android {
    namespace = "ninh.main.mybarcodescanner"
    compileSdk = 34


    defaultConfig {
        applicationId = "ninh.main.mybarcodescanner"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}


android {
    buildToolsVersion = "28.0.3" // Older versions may give compile errors
   }


dependencies {

        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.10.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
        implementation("androidx.navigation:navigation-fragment:2.7.4")
        implementation("androidx.navigation:navigation-ui:2.7.4")
        implementation("androidx.legacy:legacy-support-v4:1.0.0")
        implementation("androidx.recyclerview:recyclerview:1.3.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        implementation ("com.google.mlkit:barcode-scanning:17.2.0")
        implementation ("com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0")
        val camerax_version = "1.4.0-alpha02"
        // The following line is optional, as the core library is included indirectly by camera-camera2
        implementation("androidx.camera:camera-core:${camerax_version}")
        implementation("androidx.camera:camera-camera2:${camerax_version}")
        // If you want to additionally use the CameraX Lifecycle library
        implementation("androidx.camera:camera-lifecycle:${camerax_version}")
        // If you want to additionally use the CameraX VideoCapture library
        implementation("androidx.camera:camera-video:${camerax_version}")
    // If you want to additionally use the CameraX View class
    implementation("androidx.camera:camera-view:${camerax_version}")
    // If you want to additionally add CameraX ML Kit Vision Integration
    implementation("androidx.camera:camera-mlkit-vision:${camerax_version}")
    // If you want to additionally use the CameraX Extensions library
    implementation("androidx.camera:camera-extensions:${camerax_version}")

    //thư viện zxing
    implementation("com.journeyapps:zxing-android-embedded:3.3.0")
    implementation("androidx.appcompat:appcompat:1.0.2")

}



