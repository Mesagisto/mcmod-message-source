package org.meowcat.mesagisto.fabric.impl

import net.minecraft.network.MessageType
import net.minecraft.server.MinecraftServer
import net.minecraft.text.Text
import org.meowcat.mesagisto.fabric.api.IChat
import java.util.* // ktlint-disable no-wildcard-imports

class ChatImpl : IChat {
  override fun setServer(server: MinecraftServer) {
    TODO("Not yet implemented")
  }

  override fun broadcastMessage(message: Text, type: MessageType, senderUuid: UUID) {
    TODO("Not yet implemented")
  }
}
