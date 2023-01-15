import io.itsusinn.pkg.pkgIn
import net.fabricmc.loom.api.LoomGradleExtensionAPI

repositories {
  mavenCentral()
  mavenLocal()
  maven("https://maven.fabricmc.net/")
}

plugins {
  java
  id("dev.architectury.loom")
  id("architectury-plugin")

  id("org.jetbrains.kotlin.jvm")
  id("com.github.johnrengelman.shadow")
  id("io.itsusinn.pkg")
}
architectury {
  minecraft = "1.19.2"
  platformSetupLoomIde()
  fabric()
}
loom {
  fabricApi { }
}
pkg {
  excludePath("mappings/*")
  excludePath("META-INF/*.kotlin_module")
  excludePath("*.md")
  excludePath("DebugProbesKt.bin")
  excludePathStartWith("org/jetbrains/annotations")
  excludePathStartWith("org/intellij/lang/annotations")
  relocateKotlinxLib()
  relocateKotlinStdlib()
  kotlinRelocate("org.yaml.snakeyaml", "relocate.org.yaml.snakeyaml")
  excludePathStartWith("kotlinx/coroutines/flow")
  listOf("asn1", "jcajce", "jce", "pqc", "x509", "math", "i18n", "iana", "internal").forEach {
    excludePathStartWith("org/bouncycastle/$it")
  }

  val task = tasks.remapJar.get()
  task.dependsOn("pkg")
  shadowJar {
    task.inputFile.set(this.archiveFile)
  }
}

tasks {
  processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
      expand(mutableMapOf("version" to project.version))
    }
  }
}

dependencies {
  modImplementation("net.fabricmc:fabric-loader:0.14.8")
  modImplementation("net.fabricmc.fabric-api:fabric-api:0.58.6+1.19.2")

  minecraft("com.mojang:minecraft:1.19.2")
  mappings("net.fabricmc:yarn:1.19.2+build.3:v2")

  pkgIn(project(":common"))
  pkgIn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}
