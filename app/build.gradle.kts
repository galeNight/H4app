plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.udlnshndteringsappfortabletter"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.udlnshndteringsappfortabletter"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src\\main\\java", "src\\main\\java\\Model",
                    "src\\main\\java",
                    "src\\main\\java\\View", "src\\main\\java", "src\\main\\java\\Controller",
                    "src\\main\\java",
                    "src\\main\\java\\Model", "src\\main\\java", "src\\main\\java\\View",
                    "src\\main\\java",
                    "src\\main\\java\\Controller", "src\\main\\java", "src\\main\\java\\Model"
                )
            }
        }
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
}