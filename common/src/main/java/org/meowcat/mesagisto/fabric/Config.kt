package org.meowcat.mesagisto.fabric

data class Config(
  val enable: Boolean = false,
  val channel: String = "your-channel",
  val nats: String = "nats://itsusinn.site:4222",
  val cipher: CipherConfig = CipherConfig(),
  var idBase: Int = 0
)

data class CipherConfig(
  val key: String = "default-key"
)
