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
  minecraft("com.mojang:minecraft:1.18.2")
  mappings("net.fabricmc:yarn:1.18.2+build.1:v2")

  modCompileOnly("net.fabricmc:fabric-loader:0.11.3")
  modCompileOnly("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
  implementation("io.nats:jnats:2.14.0")
  implementation("org.meowcat:mesagisto-client-jvm:1.3.0")
  // implementation("org.meowcat:mesagisto-client:1.0.14-build")
  implementation("com.charleskorn.kaml:kaml:0.38.0")
}
