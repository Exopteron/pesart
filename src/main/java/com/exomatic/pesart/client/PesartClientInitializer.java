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
package com.exomatic.pesart.client;

import com.exomatic.pesart.client.gui.ScreenSetup;
import com.exomatic.pesart.client.render.BERenderers;
import com.exomatic.pesart.client.render.EntityRenderers;
import net.fabricmc.api.ClientModInitializer;

public class PesartClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderers.setup();
        BERenderers.setup();
        ScreenSetup.setup();
    }
}
