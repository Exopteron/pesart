package com.exomatic.pesart.mixin;

import com.exomatic.pesart.items.PesartItemsInitializer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class FlightRingMixin {
    private boolean canFly = false;
    private ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;
    @Inject(method = "playerTick", at = @At("HEAD"))
    public void tick(CallbackInfo c) {
        if (PesartItemsInitializer.FLIGHT_RING.containsFlightRing(self.getInventory()) && !this.canFly) {
            this.canFly = true;
            sync();
        } else if (this.canFly && !PesartItemsInitializer.FLIGHT_RING.containsFlightRing(self.getInventory())) {
            this.canFly = false;
            sync();
        }
        if (this.canFly && !self.getAbilities().allowFlying) {
            sync();
        }
        if (!this.canFly && self.getAbilities().allowFlying) {
            if (!self.isCreative()) {
                this.canFly = false;
                sync();
            }
        }
    }
    private void sync() {
        self.getAbilities().allowFlying = this.canFly;
        PlayerAbilities abilities = copy(self.getAbilities());
        abilities.allowFlying = this.canFly;
        if (!this.canFly) {
            abilities.flying = false;
        }
        self.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(abilities));
    }
    private PlayerAbilities copy(PlayerAbilities other) {
        PlayerAbilities abilities = new PlayerAbilities();
        abilities.setFlySpeed(other.getFlySpeed());
        abilities.setWalkSpeed(other.getWalkSpeed());
        abilities.allowFlying = other.allowFlying;
        abilities.allowModifyWorld = other.allowModifyWorld;
        abilities.creativeMode = other.creativeMode;
        abilities.flying = other.flying;
        abilities.invulnerable = other.invulnerable;
        return abilities;
    }
}
