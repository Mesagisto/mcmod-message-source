import io.itsusinn.pkg.pkgIn
import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
  java
  id("dev.architectury.loom")
  id("architectury-plugin")
  id("org.jetbrains.kotlin.jvm")
  id("com.github.johnrengelman.shadow")
  id("io.itsusinn.pkg")
}
architectury {
  minecraft = "1.16.5"
  platformSetupLoomIde()
  fabric()
}
loom {
  silentMojangMappingsLicense()
  fabricApi { }
}
pkg {
  excludePath("mappings/*")
  excludePath("META-INF/*.kotlin_module")
  excludePath("*.md")
  excludePath("DebugProbesKt.bin")
  val task = tasks.remapJar.get()
  task.dependsOn("pkg")
  shadowJar {
    task.inputFile.set(this.archiveFile)
  }
  relocateKotlinxLib()
  relocateKotlinStdlib()
  kotlinRelocate("org.yaml.snakeyaml", "$group.relocate.org.yaml.snakeyaml")
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
  modImplementation("net.fabricmc:fabric-loader:0.12.2")
  modImplementation("net.fabricmc.fabric-api:fabric-api:0.42.0+1.16")

  val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
  minecraft("com.mojang:minecraft:1.16.5")
  mappings(loom.officialMojangMappings())

  pkgIn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
  pkgIn(project(":common"))
}
