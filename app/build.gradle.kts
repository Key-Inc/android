plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("kapt")
}

android {
    namespace = "com.keyinc.keymono"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.keyinc.keymono"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val daggerVersion = "2.48"
    val hiltNavigationVersion = "1.1.0"
    val securityCryptoVersion = "1.1.0-alpha06"
    val sheetsComposeVersion = "1.3.0"
    val navVersion = "2.7.7"
    val runtimeComposeVersion = "2.7.0"
    val kotlinSerializationVersion = "1.6.0"
    val retrofitVersion = "2.8.9"
    val gsonConverterVersion = "2.9.0"
    val okHttpVersion = "4.12.0"

    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
    implementation("com.squareup.retrofit2:converter-gson:$gsonConverterVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$runtimeComposeVersion")
    implementation("androidx.security:security-crypto:$securityCryptoVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:$sheetsComposeVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:date-time:$sheetsComposeVersion")
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")
}

kapt {
    correctErrorTypes = true
}