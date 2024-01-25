plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

val appName = "Health Care"

val majorVersion = "1.0"

android {
    namespace = "com.example.healthcareapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.healthcareapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    flavorDimensions += "version"
    productFlavors {
        create("apiDev") {
            dimension = "version"
            versionNameSuffix = "-dev"
            buildConfigField("String", "BASE_URL", "\"https://localhost:8080/\"")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.compose.material:material:1.5.4")
    val composeAndroidxVersion = "2.6.2"
    val retrofitVersion = "2.9.0"
    val okHttpVersion = "5.0.0-alpha.3"
    val coilVersion = "2.5.0"
    val hiltAndroidVersion = "2.48.1"
    val hiltAndroidCompilerVersion = "2.48.1"
    val hiltCompilerVersion = "1.1.0"
    val hiltNavigationVersion = "1.1.0"
    val timberVersion = "5.0.1"
    val moshiVersion = "1.14.0"
    val datastoreVersion = "1.0.0"
    val firebaseBomVersion = "32.6.0"
    val lottieVersion = "5.2.0"
    val desugaring_version = "1.2.2"


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$desugaring_version")

    //    Coil
    implementation("io.coil-kt:coil-compose:$coilVersion")
    implementation("io.coil-kt:coil-svg:$coilVersion")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:$composeAndroidxVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$composeAndroidxVersion")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:$hiltAndroidVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltAndroidCompilerVersion")
    ksp("androidx.hilt:hilt-compiler:$hiltCompilerVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    implementation("com.squareup.moshi:moshi:${moshiVersion}")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    // Timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:$datastoreVersion")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:$firebaseBomVersion"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
}