package com.exomatic.pesart.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public interface PlayerSneakCallback {
    Event<PlayerSneakCallback> EVENT = EventFactory.createArrayBacked(PlayerSneakCallback.class, (listeners) -> (player, mode) -> {
        for (PlayerSneakCallback listener : listeners) {
            listener.run(player, mode);
        }
    });
    public static enum Mode {
        START,
        STOP
    }
    void run(PlayerEntity player, Mode mode);
}
