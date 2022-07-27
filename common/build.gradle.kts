plugins {
  java
  id("org.jetbrains.kotlin.jvm")
}
tasks.compileKotlin {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
  }
  sourceCompatibility = "1.8"
}
repositories {
  mavenCentral()
}
dependencies {

  compileOnly("org.apache.logging.log4j:log4j-api:2.17.2")

  implementation("org.fusesource.leveldbjni:leveldbjni-all:1.8")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.2")

  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.3")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.13.3")

  implementation("io.nats:jnats:2.15.3")
  implementation("org.bouncycastle:bcprov-jdk15on:1.70")
  implementation("org.mesagisto:mesagisto-client:1.5.2")
}
