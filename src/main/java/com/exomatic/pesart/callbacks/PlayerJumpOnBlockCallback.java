package com.exomatic.pesart.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface PlayerJumpOnBlockCallback {
    Event<PlayerJumpOnBlockCallback> EVENT = EventFactory.createArrayBacked(PlayerJumpOnBlockCallback.class, (listeners) -> (player, blockstate, pos) -> {
        for (PlayerJumpOnBlockCallback listener : listeners) {
            listener.run(player, blockstate, pos);
        }
    });
    void run(PlayerEntity player, BlockState blockstate, BlockPos pos);
}
