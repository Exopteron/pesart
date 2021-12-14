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
package com.exomatic.pesart.mixin.client;

import com.exomatic.pesart.network.packet.PacketJumpOnBlock;
import com.exopteron.network.ExoNetworkManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3i;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "jump", at = @At("HEAD"))
    public void jump(CallbackInfo c) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (self == mc.player) {
            ExoNetworkManager.INSTANCE.sendPacketToServer(new PacketJumpOnBlock(self.getBlockPos().subtract(new Vec3i(0, 1, 0))));
        }
    }
}
