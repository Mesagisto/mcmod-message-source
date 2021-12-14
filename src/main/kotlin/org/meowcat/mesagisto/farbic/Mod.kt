package org.meowcat.mesagisto.farbic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.network.MessageType
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import org.apache.logging.log4j.LogManager
import org.meowcat.mesagisto.client.Logger
import org.meowcat.mesagisto.farbic.handlers.send
import java.util.* // ktlint-disable no-wildcard-imports
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.io.path.Path

object Mod : ModInitializer, CoroutineScope {
  internal val LOGGER = LogManager.getLogger()
  lateinit var server: MinecraftServer
  override val coroutineContext = EmptyCoroutineContext

  private val configKeeper =
    ConfigKeeper.create(Path("config/mesagisto.yml")) { Config() }

  val CONFIG = configKeeper.value

  override fun onInitialize() {
    Logger.bridgeToLog4j(LOGGER)
    if (!CONFIG.enable) {
      Logger.info { "信使插件未启用" }
      return
    }
    // todo
    ServerLifecycleEvents.SERVER_STARTED.register {
      server = it
    }
    ServerLifecycleEvents.SERVER_STOPPING.register {
      configKeeper.save()
    }
  }

  fun onServerChat(
    sender: ServerPlayerEntity,
    content: String
  ) = runBlocking {
    if (!CONFIG.enable) return@runBlocking
    send(sender, content)
  }
}
fun Mod.broadcastMessage(
  message: Text,
  type: MessageType = MessageType.CHAT,
  senderUuid: UUID = UUID.randomUUID()
) {
  server.playerManager.broadcastChatMessage(message, type, senderUuid)
}
fun Mod.broadcastMessage(
  message: String,
  type: MessageType = MessageType.CHAT,
  senderUuid: UUID = UUID.randomUUID()
) = broadcastMessage(LiteralText(message), type, senderUuid)