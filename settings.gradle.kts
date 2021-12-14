rootProject.name = "farbic-message-source"
pluginManagement {
  repositories {
    maven("https://maven.fabricmc.net/") {
      name = "Fabric"
    }
    gradlePluginPortal()
    mavenLocal()
    maven("./maven-repo")
    plugins {
      id("fabric-loom") version "0.8-SNAPSHOT"
      id("org.jetbrains.kotlin.jvm") version "1.6.0"
    }
  }
}