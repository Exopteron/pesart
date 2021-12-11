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
