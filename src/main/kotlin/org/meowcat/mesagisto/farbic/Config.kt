@file:OptIn(InternalSerializationApi::class)
package org.meowcat.mesagisto.farbic

import kotlinx.serialization.* // ktlint-disable no-wildcard-imports
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

val Yaml = com.charleskorn.kaml.Yaml.default
class ConfigKeeper<C : Any> (
  val value: @Serializable C,
  private val serializer: KSerializer<C>,
  private val path: Path
) {

  fun save() {
    val clazz = value::class
    val str = Yaml.encodeToString(serializer, value)
    path.writeText(str)
  }
  companion object {

    inline fun <reified T : Any> create(
      path: Path,
      defaultValue: () -> T
    ): ConfigKeeper<T> {
      val value = if (path.exists()) {
        // todo log
        try {
          Yaml.decodeFromString(T::class.serializer(), path.readText())
        } catch (_: Throwable) {
          val default = defaultValue()
          val str = Yaml.encodeToString(T::class.serializer(), default)
          path.writeText(str)
          default
        }
      } else {
        val default = defaultValue()
        val str = Yaml.encodeToString(T::class.serializer(), default)
        path.writeText(str)
        default
      }
      return ConfigKeeper(value, T::class.serializer(), path)
    }
  }
}

@Serializable
data class Config(
  val enable: Boolean = false,
  val channel: String = "your-channel",
  val nats: String = "nats://itsusinn.site:4222",
  val cipher: CipherConfig = CipherConfig(),
  var idBase: Int = 0
)
@Serializable
data class CipherConfig(
  val enable: Boolean = true,
  val key: String = "default-key",
  @SerialName("refuse-plain")
  val refusePlain: Boolean = true
)
