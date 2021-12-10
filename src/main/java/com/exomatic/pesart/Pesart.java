package com.exomatic.pesart;

import net.fabricmc.api.ModInitializer;

import com.exomatic.pesart.blocks.PesartBlocksInitializer;
import com.exomatic.pesart.commands.PesartCommandsInitializer;
import com.exomatic.pesart.entities.PesartEntitiesInitializer;
import com.exomatic.pesart.fluids.PesartFluidsInitializer;
import com.exomatic.pesart.items.PesartItemsInitializer;
import com.exomatic.pesart.network.PesartNetworkInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pesart implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

	@Override
	public void onInitialize() {
		PesartItemsInitializer.setup();
		PesartBlocksInitializer.setup();
		PesartNetworkInitializer.setup();
		PesartFluidsInitializer.setup();
		PesartEntitiesInitializer.setup();
		PesartCommandsInitializer.setup();
	}
}
