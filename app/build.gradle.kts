plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.gms)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("androidx.navigation.safeargs")

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
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    
    // Glide
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Choose one version of play-services-auth
    implementation("com.google.android.gms:play-services-auth:21.2.0") // or 20.5.0, but not both

    // Add the correct RxJava dependency
    implementation(libs.rxjava) // Replace with the correct version
    implementation (libs.adapter.rxjava3)
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.0") // Use the latest stable version
    implementation ("androidx.recyclerview:recyclerview:1.2.1") // Use the latest stable version

    // CardView
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    //Glide
    implementation (libs.com.github.bumptech.glide.glide2)
    annotationProcessor (libs.glide.compiler)

    // CircleImageView
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // athuth
    implementation("com.google.android.gms:play-services-auth:20.5.0")
    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.1")
    implementation("com.google.firebase:firebase-auth-ktx:22.0.0")
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

     // Navigation
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")

    // Youtube player
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0")

    // Material Components
    implementation ("com.google.android.material:material:1.9.0")

    // Room
    implementation ("androidx.room:room-runtime:2.5.1")
    annotationProcessor ("androidx.room:room-compiler:2.5.1")
    implementation ("androidx.room:room-rxjava3:2.5.1")

    // RxJava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")

    // Firebase
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-storage")

    implementation ("com.google.android.material:material:")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5")

    // Lottie
    implementation ("com.airbnb.android:lottie:2.8.0")
    implementation ("com.airbnb.android:lottie:6.0.0")

     //Authentication with Credential Manager
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    implementation ("com.google.android.material:material:1.9.0")

}
