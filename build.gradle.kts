plugins {
  id("fabric-loom") version "0.8-SNAPSHOT"
  id("org.jetbrains.kotlin.jvm") version ("1.6.0")
  id("org.jetbrains.kotlin.plugin.serialization")version ("1.6.0")
  id("com.github.johnrengelman.shadow")version ("7.1.0")
  // id("org.meowcat.kato") version "0.1.0-dev24"
}

group = property("maven_group")!!
version = property("mod_version")!!

repositories {
  mavenCentral()
  maven("https://jitpack.io")
}

dependencies {
  minecraft("com.mojang:minecraft:${property("minecraft_version")}")
  mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")

  modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")

  modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
  implementation("io.nats:jnats:2.13.1")
  implementation("org.meowcat:mesagisto-client-jvm:1.0.12")
  implementation("io.arrow-kt:arrow-core:1.0.0")
  implementation("com.charleskorn.kaml:kaml:0.37.0")
}
tasks {
  processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
      expand(mutableMapOf("version" to project.version))
    }
  }
  compileKotlin {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
    }
    sourceCompatibility = "1.8"
  }
}
