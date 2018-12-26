import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.11"
}
val junitJupiterVersion by extra("5.3.2")

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "failed", "skipped")
        }
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testCompile("org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}")
    testCompile("org.junit.jupiter:junit-jupiter-params:${junitJupiterVersion}")
    testCompile("org.junit.jupiter:junit-jupiter-engine:${junitJupiterVersion}")
    testCompile("com.approvaltests:approvaltests:2.0.1")
    compile(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}