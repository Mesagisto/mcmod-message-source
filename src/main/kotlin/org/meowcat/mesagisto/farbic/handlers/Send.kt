package org.meowcat.mesagisto.farbic.handlers

import arrow.core.left
import io.nats.client.impl.NatsMessage
import net.minecraft.server.network.ServerPlayerEntity
import org.meowcat.mesagisto.client.Server
import org.meowcat.mesagisto.client.data.Message
import org.meowcat.mesagisto.client.data.MessageType
import org.meowcat.mesagisto.client.data.Packet
import org.meowcat.mesagisto.client.data.Profile
import org.meowcat.mesagisto.client.toByteArray
import org.meowcat.mesagisto.farbic.IdGen
import org.meowcat.mesagisto.farbic.Mod.CONFIG

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
