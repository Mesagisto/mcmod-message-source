plugins {
  java
  id("fabric-loom")
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}
tasks.compileKotlin {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
  }
  sourceCompatibility = "1.8"
}

dependencies {
  modCompileOnly("net.fabricmc:fabric-loader:${property("loader_version")}")
  modCompileOnly("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

  minecraft("com.mojang:minecraft:1.16.5")
  mappings("net.fabricmc:yarn:1.16.5+build.1:v2")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
  implementation("io.nats:jnats:${property("nats_version")}")
  implementation("org.meowcat:mesagisto-client-jvm:${property("mesagisto_client_version")}")
  // implementation("org.meowcat:mesagisto-client:1.0.14-build")
  implementation("com.charleskorn.kaml:kaml:0.38.0")
}
