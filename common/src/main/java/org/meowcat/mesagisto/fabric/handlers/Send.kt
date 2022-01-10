package org.meowcat.mesagisto.fabric.handlers

import io.nats.client.impl.NatsMessage
import net.minecraft.server.network.ServerPlayerEntity
import org.meowcat.mesagisto.client.Server
import org.meowcat.mesagisto.client.data.* // ktlint-disable no-wildcard-imports
import org.meowcat.mesagisto.client.toByteArray
import org.meowcat.mesagisto.fabric.IdGen
import org.meowcat.mesagisto.fabric.Mod.CONFIG

suspend fun send(
  sender: ServerPlayerEntity,
  content: String
) {
  val channel = CONFIG.channel
  val msgId = IdGen.gen()
  val chain = listOf<MessageType>(
    MessageType.Text(content)
  )
  val message = Message(
    profile = Profile(
      // fixme
      ByteArray(0),
      sender.name.asString(),
      sender.displayName.asString()
    ),
    id = msgId.toByteArray(),
    chain = chain
  )
  val packet = Packet.from(message.left())

  Server.sendAndRegisterReceive(0L, channel, packet) receive@{ it, _ ->
    return@receive receive(it as NatsMessage)
  }
}
