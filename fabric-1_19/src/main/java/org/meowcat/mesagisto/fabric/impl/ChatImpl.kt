package org.meowcat.mesagisto.fabric.impl

import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.Component
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import org.mesagisto.mcmod.api.ChatHandler
import org.mesagisto.mcmod.api.IChat

class IChatImpl : IChat by ChatImpl

object ChatImpl : IChat {
  lateinit var server: MinecraftServer
  private val handlers: MutableList<ChatHandler> = arrayListOf()

  fun deliverChatEvent(player: ServerPlayer, content: Component) {
    handlers.forEach {
      it.hande(player.name.string, content.string)
    }
  }
  override fun broadcastMessage(message: String) {
    server.playerList.broadcastSystemMessage(Component.literal(message), ChatType.SYSTEM)
  }
  override fun registerChatHandler(callback: ChatHandler) {
    handlers.add(callback)
  }
}
