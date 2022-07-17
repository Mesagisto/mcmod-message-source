plugins {
  java
  id("fabric-loom")
  id("org.jetbrains.kotlin.jvm")
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

  implementation("org.fusesource.leveldbjni:leveldbjni-all:1.8")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.2")

  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.3")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.13.3")

  implementation("io.nats:jnats:2.15.3")
  implementation("org.bouncycastle:bcprov-jdk15on:1.70")
  implementation("org.mesagisto:mesagisto-client:1.5.1")
}
