plugins {
  java
  id("fabric-loom") version "0.10-SNAPSHOT"
  id("org.jetbrains.kotlin.jvm") version ("1.6.0")
  id("org.jetbrains.kotlin.plugin.serialization")version ("1.6.0")
  id("com.github.johnrengelman.shadow")version ("7.1.0")
  id("io.itsusinn.pkg") version "1.2.0"
}
allprojects {
  group = property("maven_group")!!
  version = property("mod_version")!!
  repositories {
    mavenCentral()
    maven("https://jitpack.io")
    mavenLocal()
    maven("https://maven.fabricmc.net/")
  }
//  java {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//    targetCompatibility = JavaVersion.VERSION_1_8
//  }
//  tasks.compileKotlin {
//    kotlinOptions {
//      jvmTarget = "1.8"
//      freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
//    }
//    sourceCompatibility = "1.8"
//  }
}
dependencies {
  minecraft("com.mojang:minecraft:1.16.5")
  mappings("net.fabricmc:yarn:1.16.5+build.1:v2")
}
