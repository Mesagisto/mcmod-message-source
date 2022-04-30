rootProject.name = "fabric-message-source"
pluginManagement {
  repositories {
    maven("https://maven.fabricmc.net/") {
      name = "Fabric"
    }
    gradlePluginPortal()
    mavenLocal()
    maven("https://github.com/Itsusinn/maven-repo/raw/master/")
  }
}
include("common")
include("1_16", "1_17", "1_18")
