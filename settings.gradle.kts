rootProject.name = "farbic-message-source"
pluginManagement {
  repositories {
    maven("https://maven.fabricmc.net/") {
      name = "Fabric"
    }
    gradlePluginPortal()
    mavenLocal()
    maven("https://github.com/Itsusinn/maven-repo/raw/master/")
    plugins {
      id("fabric-loom") version "0.10-SNAPSHOT"
      id("org.jetbrains.kotlin.jvm") version "1.6.0"
    }
  }
}
include("common")
include("1_16_5")
