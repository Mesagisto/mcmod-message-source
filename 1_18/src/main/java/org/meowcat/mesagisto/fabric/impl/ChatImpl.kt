package org.meowcat.mesagisto.fabric.impl

import net.minecraft.network.MessageType
import net.minecraft.server.MinecraftServer
import net.minecraft.text.Text
import org.meowcat.mesagisto.fabric.api.IChat
import java.util.* // ktlint-disable no-wildcard-imports

class ChatImpl : IChat {
  private lateinit var server: MinecraftServer
  override fun setServer(server: MinecraftServer) {
    this.server = server
  }

  override fun broadcastMessage(message: Text, type: MessageType, senderUuid: UUID) {
    server.playerManager.broadcast(message, type, senderUuid)
  }
}
