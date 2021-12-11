package com.exomatic.pesart.client;

import com.exopteron.network.ClientNetworking;
import com.exopteron.network.ExoNetworkManager;
import com.exopteron.network.ExoPacket;
import com.exopteron.network.Side;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

public class PesartClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientNetworking.registerClientNetHandler();
    }
}
