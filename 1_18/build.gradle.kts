import io.itsusinn.pkg.pkgIn

plugins {
  java
  id("fabric-loom")
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("com.github.johnrengelman.shadow")
  id("io.itsusinn.pkg")
}
pkg {
  excludePath("mappings/*")
  excludePath("META-INF/*.kotlin_module")
  excludePath("*.md")
  excludePath("DebugProbesKt.bin")
  shadowJar {
    tasks.remapJar {
      val task = this@shadowJar
      dependsOn(task)
      mustRunAfter(task)

//      this.inputFile.set(task.outputs.files.singleFile)
      this.input.set(task.outputs.files.singleFile)
      // 这里需要借助fabric-loom进行remap
    }
    minimize()
  }
  relocateKotlinxLib()
  relocateKotlinStdlib()
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
  modImplementation("net.fabricmc.fabric-api:fabric-api:0.29.3+1.16")

  minecraft("com.mojang:minecraft:1.18.2")
  mappings("net.fabricmc:yarn:1.18.2+build.1:v2")

  pkgIn(project(":common"))
  pkgIn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
  pkgIn("io.nats:jnats:2.14.0")
  pkgIn("org.meowcat:mesagisto-client-jvm:1.3.0")
  pkgIn("com.charleskorn.kaml:kaml:0.43.0")
}
