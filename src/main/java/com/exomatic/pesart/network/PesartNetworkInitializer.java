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
package com.exomatic.pesart.network;

import com.exomatic.pesart.network.packet.PacketJump;
import com.exomatic.pesart.network.packet.PacketJumpOnBlock;
import com.exomatic.pesart.network.packet.PacketSetShelfConfig;
import com.exopteron.network.ExoNetworkManager;

public class PesartNetworkInitializer {
    private static int packetID = 0;
    public static void setup() {
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketJumpOnBlock.class);
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketJump.class);
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketSetShelfConfig.class);
        /*
            for prism:
            if you want to register a packet, do 
            ExoNetworkManager.INSTANCE.registerPacket(packetID++, (class that implements ExoPacket).class);
        */
    }
}
