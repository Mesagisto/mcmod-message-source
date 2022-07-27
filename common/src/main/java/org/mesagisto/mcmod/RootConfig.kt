package org.mesagisto.mcmod

data class RootConfig(
  val enable: Boolean = false,
  val channel: String = "your-channel",
  val target: String = "target-name",
  val nats: String = "nats://nats.mesagisto.org:4222",
  val cipher: CipherConfig = CipherConfig()
)

data class CipherConfig(
  val key: String = "default-key"
)
