package com.exomatic.pesart.network;

import com.exomatic.pesart.network.packet.PacketJump;
import com.exomatic.pesart.network.packet.PacketJumpOnBlock;
import com.exopteron.network.ExoNetworkManager;

public class PesartNetworkInitializer {
    private static int packetID = 0;
    public static void setup() {
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketJumpOnBlock.class);
        ExoNetworkManager.INSTANCE.registerPacket(packetID++, PacketJump.class);
        /*
            for prism:
            if you want to register a packet, do 
            ExoNetworkManager.INSTANCE.registerPacket(packetID++, (class that implements ExoPacket).class);
        */
    }
}
