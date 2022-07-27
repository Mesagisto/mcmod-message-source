package org.mesagisto.mcmod.handlers

import org.meowcat.mesagisto.client.Server
import org.meowcat.mesagisto.client.data.* // ktlint-disable no-wildcard-imports
import org.meowcat.mesagisto.client.toByteArray
import org.mesagisto.mcmod.ModEntry.CONFIG
import org.mesagisto.mcmod.ModEntry.DATA

suspend fun send(
  sender: String,
  content: String
) {
  val channel = CONFIG.channel
  val msgId = DATA.idCounter
  val chain = listOf<MessageType>(
    MessageType.Text(content)
  )
  val message = Message(
    profile = Profile(
      ByteArray(0),
      sender,
      null
    ),
    id = msgId.getAndIncrement().toByteArray(),
    chain = chain
  )
  val packet = Packet.from(message.left())

  Server.send(CONFIG.target, channel, packet)
}
