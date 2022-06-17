
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.deutschebahn.test"
        minSdk = 26
        targetSdk = 32
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
        jvmTarget = Config.jvmTargetVersion
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("**/attach_hotspot_windows.dll")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/licenses/**")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${Versions.AndroidX.core}")
    implementation("androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}")
    implementation("com.google.android.material:material:${Versions.material}")

    api("io.mockk:mockk:${Versions.mockk}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}")
    api("androidx.arch.core:core-testing:${Versions.AndroidArch.coreTesting}")

    testImplementation("junit:junit:4.13.2")
    implementation("io.insert-koin:koin-test:${Versions.koin}")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}