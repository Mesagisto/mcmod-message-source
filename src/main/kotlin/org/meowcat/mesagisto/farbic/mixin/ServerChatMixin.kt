package org.meowcat.mesagisto.farbic.mixin

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import org.meowcat.mesagisto.farbic.Mod
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(ServerPlayNetworkHandler::class)
class ServerChatMixin {

  @Shadow
  lateinit var player: ServerPlayerEntity

  @Inject(method = ["onGameMessage(Lnet/minecraft/network/packet/c2s/play/ChatMessageC2SPacket;)V"], at = [At(value = "TAIL")])
  fun onChat(packet: ChatMessageC2SPacket, ci: CallbackInfo) {
    Mod.onServerChat(player, packet.chatMessage)
  }
}
