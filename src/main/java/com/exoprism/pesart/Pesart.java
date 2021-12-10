package com.exoprism.pesart;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pesart implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}
