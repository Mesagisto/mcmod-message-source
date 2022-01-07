plugins {
  id("fabric-loom") version "0.8-SNAPSHOT"
  id("org.jetbrains.kotlin.jvm") version ("1.6.0")
  id("org.jetbrains.kotlin.plugin.serialization")version ("1.6.0")
  id("com.github.johnrengelman.shadow")version ("7.1.0")
  id("org.meowcat.kato") version "0.1.0-dev31"
}

group = property("maven_group")!!
version = property("mod_version")!!

repositories {
  mavenCentral()
  maven("https://jitpack.io")
  mavenLocal()
}
kato {
  excludePath("kotlin/*")
  excludePath("kotlinx/coroutines/*")
  excludePath("kotlinx/serialization/*")
  excludePath("mappings/*")
  excludePath("META-INF/*.kotlin_module")
  excludePath("*.md")
  excludePath("DebugProbesKt.bin")
  excludeGroups(
    "org.lwjgl",
    "net.fabricmc",
    "org.ow2.asm",
    "net.minecraft",
    "com.mojang",
    "net.java.dev.jna",
    "com.google.jimfs",
    "org.jetbrains.kotlinx",
    "org.jetbrains.kotlin",
    "org.apache.logging.log4j",
    "net.java.jinput",
    "net.java.jutils",
    "org.apache.commons",
    "org.apache.httpcomponents",
    "commons-logging",
    "com.ibm.icu",
    "it.unimi.dsi",
    "ca.weblite",
    "net.minecrell",
    "com.google.guava",
    "commons-io",
    "commons-codec",
    "com.google.code.gson",
    "oshi-project",
    "io.netty",
    "net.sf.jopt-simple",
    "net_fabricmc_yarn_1_16_5_1_16_5_build_1_v2.net.fabricmc.fabric-api",
    "net_fabricmc_yarn_1_16_5_1_16_5_build_1_v2.net.fabricmc",
  )
  shadowJar {
    minimize()
  }
}
java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(16))
  }
}
dependencies {
  minecraft("com.mojang:minecraft:${property("minecraft_version")}")
  mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")

  modCompileClasspath("net.fabricmc:fabric-loader:${property("loader_version")}")

  modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

  compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
  implementation("io.nats:jnats:2.13.1")
  implementation("org.meowcat:mesagisto-client-jvm:1.1.0")
  // implementation("org.meowcat:mesagisto-client:1.0.14-build")
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
