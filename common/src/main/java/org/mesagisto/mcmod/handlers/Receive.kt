package org.mesagisto.mcmod.handlers

import io.nats.client.impl.NatsMessage
import org.meowcat.mesagisto.client.Base64
import org.meowcat.mesagisto.client.Server
import org.meowcat.mesagisto.client.data.Either
import org.meowcat.mesagisto.client.data.Message
import org.meowcat.mesagisto.client.data.MessageType
import org.meowcat.mesagisto.client.data.Packet
import org.mesagisto.mcmod.ModEntry
import org.mesagisto.mcmod.ModEntry.CONFIG
import org.mesagisto.mcmod.api.ChatImpl

object Receive {
  suspend fun recover() {
    Server.recv(CONFIG.target, ModEntry.CONFIG.channel) handler@{ msg, _ ->
      return@handler mainHandler(msg as NatsMessage)
    }
  }
}

suspend fun mainHandler(
  message: NatsMessage
): Result<Unit> = runCatching {
  when (val packet = Packet.fromCbor(message.data).getOrThrow()) {
    is Either.Left -> {
      leftSubHandler(packet.value).getOrThrow()
    }
    is Either.Right -> {
      packet.value
    }
  }
}
fun leftSubHandler(
  message: Message
): Result<Unit> = runCatching fn@{
  val senderName = with(message.profile) { nick ?: username ?: Base64.encodeToString(id) }
  val msgList = message.chain.filterIsInstance<MessageType.Text>()
  msgList.forEach {
    val text = "<$senderName> ${it.content}"
    ChatImpl.broadcastMessage(text)
  }
}
