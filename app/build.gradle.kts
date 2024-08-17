plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.gms)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.eatsease"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eatsease"
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

    // Use a single buildFeatures block
    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Choose one version of play-services-auth
    implementation("com.google.android.gms:play-services-auth:21.2.0") // or 20.5.0, but not both

    // Material Components dependency (already specified in the implementation section)
    implementation("com.google.android.material:material:1.9.0")

    // Add the correct RxJava dependency
    implementation(libs.rxjava) // Replace with the correct version
    implementation (libs.adapter.rxjava3)
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")



    implementation ("androidx.recyclerview:recyclerview:1.3.0") // Use the latest stable version
    implementation ("androidx.recyclerview:recyclerview:1.2.1") // Use the latest stable version

    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation (libs.com.github.bumptech.glide.glide2)
    annotationProcessor (libs.glide.compiler)


}
