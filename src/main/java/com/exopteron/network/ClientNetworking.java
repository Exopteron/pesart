package com.exopteron.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientNetworking {
    public static void registerClientNetHandler() {
        ClientPlayNetworking.registerGlobalReceiver(ExoNetworkManager.INSTANCE.channel, (client, handler, buf, responseSender) -> {
            int packetID = buf.readVarInt();
            Class<? extends ExoPacket> packet = ExoNetworkManager.INSTANCE.idToPackets.get(packetID);
            if (packet != null) {
                try {
                    ExoPacket instance = packet.getConstructor().newInstance();
                    instance.read(buf);
                    client.execute(() -> {
                            instance.handle(client.player, Side.LOGICAL_CLIENT);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO handle gracefully
                }
            }
        });
    }
}
