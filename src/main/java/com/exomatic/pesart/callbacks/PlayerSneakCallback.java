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
