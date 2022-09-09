import io.itsusinn.pkg.pkgIn
import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
  java
  kotlin("jvm")
  id("architectury-plugin")
  id("dev.architectury.loom")
  id("com.github.johnrengelman.shadow")
  id("io.itsusinn.pkg")
}
architectury {
  minecraft = "1.18.2"
  platformSetupLoomIde()
  forge()
}
repositories {
  maven("https://files.minecraftforge.net/maven")
  maven("https://maven.minecraftforge.net")
  maven("https://maven.parchmentmc.org")
  mavenCentral()
}
pkg {
  excludePath("META-INF/*.kotlin_module")
  excludePathStartWith("META-INF/versions")
  excludePathStartWith("META-INF/proguard")
  excludePathStartWith("META-INF/maven")
  excludePathStartWith("org/slf4j")
  excludePathStartWith("kotlinx/coroutines/flow")
  listOf("asn1", "jcajce", "jce", "pqc", "x509", "math", "i18n", "iana", "internal").forEach {
    excludePathStartWith("org/bouncycastle/$it")
  }
  val task = tasks.remapJar.get()
  task.dependsOn("pkg")
  shadowJar {
    task.inputFile.set(this.archiveFile)
  }
  relocateKotlinStdlib()
  relocateKotlinxLib()
  kotlinRelocate("org.yaml.snakeyaml", "$group.relocate.org.yaml.snakeyaml")
}

loom {
  silentMojangMappingsLicense()
}

dependencies {
  val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
  minecraft("com.mojang:minecraft:1.18.2")
  mappings(loom.officialMojangMappings())

  forge("net.minecraftforge:forge:1.18.2-40.0.12")
  compileOnly("org.jetbrains.kotlin:kotlin-stdlib")

  pkgIn(project(":common"))
  pkgIn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
  pkgIn("io.nats:jnats:2.15.3")
  pkgIn("org.mesagisto:mesagisto-client:1.5.2")
}

java {
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
  compileKotlin {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
    }
    sourceCompatibility = "1.8"
  }
  processResources {
    inputs.property("version", project.version)
    filesMatching("META-INF/mods.toml") {
      expand("version" to project.version)
    }
  }
}
