plugins {
    java
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
}