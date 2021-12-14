/* 
Copyright (c) 2021 Exopteron, PrismaticYT

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
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
