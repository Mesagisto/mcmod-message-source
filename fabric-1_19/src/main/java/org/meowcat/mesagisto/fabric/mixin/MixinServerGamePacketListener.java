package org.meowcat.mesagisto.fabric.mixin;

import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.FilteredText;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.meowcat.mesagisto.fabric.impl.ChatImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class MixinServerGamePacketListener {
    @Shadow
    public ServerPlayer player;

    @Inject(method = "broadcastChatMessage",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/chat/PlayerChatMessage;verify(Lnet/minecraft/server/level/ServerPlayer;)Z")
    )
    private void handleChat(FilteredText<PlayerChatMessage> filteredText, CallbackInfo ci) {
        PlayerChatMessage chatMessage = filteredText.raw();
        ChatImpl.INSTANCE.deliverChatEvent(player,chatMessage.serverContent());
    }
}
