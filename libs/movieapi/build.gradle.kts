plugins {
    kotlin("jvm")
}

tasks {
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    test {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "FAILED", "SKIPPED")
            showExceptions = true
        }
    }
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.google.code.gson:gson:${Versions.gson}")

    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")

    implementation("androidx.annotation:annotation:${Versions.AndroidX.annotation}") {
        isTransitive = false
    }

    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")

    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junitJupiter}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Versions.junitJupiter}")

    testImplementation("com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}