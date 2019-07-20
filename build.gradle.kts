import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.jpa") version "1.3.31"
    id("org.springframework.boot") version "2.2.0.M4"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.3.31"
    kotlin("plugin.spring") version "1.3.31"
}


group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8
val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://dl.bintray.com/konrad-kaminski/maven")}
    maven { url = uri("https://repo.spring.io/snapshot") }
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.6.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0-M2")

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.0-M2")

    implementation("org.springframework.kotlin:spring-kotlin-coroutine:0.3.7")
    implementation("org.springframework.kotlin:spring-webmvc-kotlin-coroutine:0.3.7")

    implementation("com.squareup.okhttp3:logging-interceptor:4.0.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "junit", module = "junit")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
