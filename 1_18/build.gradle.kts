import io.itsusinn.pkg.pkgIn

plugins {
  java
  id("fabric-loom")
  id("org.jetbrains.kotlin.jvm")
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
      this.input.set(task.outputs.files.singleFile)
      // 这里需要借助fabric-loom进行remap
    }
    minimize()
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
  modImplementation("net.fabricmc.fabric-api:fabric-api:0.29.3+1.16")

  minecraft("com.mojang:minecraft:1.18.2")
  mappings("net.fabricmc:yarn:1.18.2+build.1:v2")

  pkgIn(project(":common"))
  pkgIn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
  pkgIn("io.nats:jnats:2.15.3")
  pkgIn("org.mesagisto:mesagisto-client:1.5.1")
}
