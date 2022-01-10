package org.meowcat.mesagisto.fabric.api

import net.minecraft.network.MessageType
import net.minecraft.server.MinecraftServer
import net.minecraft.text.Text
import java.util.* // ktlint-disable no-wildcard-imports

interface IChat {
  fun setServer(server: MinecraftServer)
  fun broadcastMessage(
    message: Text,
    type: MessageType = MessageType.CHAT,
    senderUuid: UUID = UUID.randomUUID()
  )
}
