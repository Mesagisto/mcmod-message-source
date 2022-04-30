package org.meowcat.mesagisto.fabric.mixin;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.meowcat.mesagisto.fabric.Mod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerChatMixin {
	@Shadow
	public ServerPlayerEntity player;

	@Inject(at = @At("INVOKE"), method = "onGameMessage(Lnet/minecraft/network/packet/c2s/play/ChatMessageC2SPacket;)V")
	public void onChat(ChatMessageC2SPacket packet, CallbackInfo ci) {
		ServerPlayerEntity player = this.player;
		String message = packet.getChatMessage();
		Mod.INSTANCE.onServerChat(player, message);
	}
}
