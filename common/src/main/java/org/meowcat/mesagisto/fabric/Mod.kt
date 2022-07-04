package org.meowcat.mesagisto.fabric

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
import org.meowcat.mesagisto.client.MesagistoConfig
import org.meowcat.mesagisto.client.utils.ConfigKeeper
import org.meowcat.mesagisto.fabric.api.IChat
import org.meowcat.mesagisto.fabric.handlers.Receive
import org.meowcat.mesagisto.fabric.handlers.send
import java.util.* // ktlint-disable no-wildcard-imports
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.io.path.Path

object Mod : ModInitializer, CoroutineScope {
  private val LOGGER = LogManager.getLogger()
  lateinit var server: MinecraftServer
  override val coroutineContext = EmptyCoroutineContext

  private val configKeeper =
    ConfigKeeper.create(Path("mods/mesagisto/config.yml")) { Config() }

  private val chat = ServiceLoader.load(IChat::class.java).first()

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
      chatImpl.setServer(it)
    }
    ServerLifecycleEvents.SERVER_STOPPING.register {
      configKeeper.save()
    }
    MesagistoConfig.builder {
      name = "fabric"
      natsAddress = CONFIG.nats
      cipherKey = CONFIG.cipher.key
    }.apply()
    runBlocking {
      Receive.recover()
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
  chatImpl.broadcastMessage(message, type, senderUuid)
}
fun Mod.broadcastMessage(
  message: String,
  type: MessageType = MessageType.CHAT,
  senderUuid: UUID = UUID.randomUUID()
) {
  broadcastMessage(LiteralText(message), type, senderUuid)
}

val chatImpl = run {
  val loader = ServiceLoader.load(IChat::class.java)
  loader.findFirst().get()
}
