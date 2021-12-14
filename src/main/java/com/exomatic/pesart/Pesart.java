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
package com.exomatic.pesart;

import net.fabricmc.api.ModInitializer;

import com.exomatic.pesart.blocks.PesartBlocksInitializer;
import com.exomatic.pesart.callbacks.PesartCallbackInitializer;
import com.exomatic.pesart.commands.PesartCommandsInitializer;
import com.exomatic.pesart.entities.PesartEntitiesInitializer;
import com.exomatic.pesart.fluids.PesartFluidsInitializer;
import com.exomatic.pesart.items.PesartItemsInitializer;
import com.exomatic.pesart.network.PesartNetworkInitializer;
import com.exomatic.pesart.screen.PesartScreenInitializer;

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
		PesartCallbackInitializer.setup();
		PesartScreenInitializer.setup();
	}
}
