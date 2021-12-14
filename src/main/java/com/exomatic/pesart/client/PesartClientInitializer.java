package com.exomatic.pesart.client;

import com.exomatic.pesart.client.gui.ScreenSetup;
import com.exomatic.pesart.client.render.BERenderers;
import com.exomatic.pesart.client.render.EntityRenderers;
import com.exopteron.network.ClientNetworking;
import net.fabricmc.api.ClientModInitializer;

public class PesartClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientNetworking.registerClientNetHandler();
        EntityRenderers.setup();
        BERenderers.setup();
        ScreenSetup.setup();
    }
}
