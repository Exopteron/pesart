package com.exomatic.pesart.client;

import com.exopteron.network.ClientNetworking;
import net.fabricmc.api.ClientModInitializer;

public class PesartClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientNetworking.registerClientNetHandler();
    }
}
