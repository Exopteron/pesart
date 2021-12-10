package com.exomatic.pesart.network;

public class PesartNetworkInitializer {
    private static int packetID = 0;
    public static void setup() {
        /*
            for prism:
            if you want to register a packet, do 
            ExoNetworkManager.INSTANCE.registerPacket(packetID++, (class that implements IExoPacket).class);
        */
    }
}
