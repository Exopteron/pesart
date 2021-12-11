package com.exomatic.pesart.mixin;

import com.exomatic.pesart.callbacks.PlayerSneakCallback;
import com.exomatic.pesart.callbacks.PlayerSneakCallback.Mode;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(method = "onClientCommand", at = @At("TAIL"))
    public void onClientCommand(ClientCommandC2SPacket packet, CallbackInfo c) {
        PlayerEntity player = ((ServerPlayNetworkHandler) (Object) this).player;
        switch (packet.getMode()) {
            case PRESS_SHIFT_KEY: {
                PlayerSneakCallback.EVENT.invoker().run(player, Mode.START);
                break;
            }
            case RELEASE_SHIFT_KEY: {
                PlayerSneakCallback.EVENT.invoker().run(player, Mode.STOP);
                break;
            }
            default: {
                break;
            }
        }
    }
}
