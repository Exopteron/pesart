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
package com.exomatic.pesart.blocks.entities;

import java.util.Iterator;
import java.util.Random;

import com.exomatic.pesart.client.gui.ExoFurnaceGui;

import io.netty.util.internal.ThreadLocalRandom;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExoFurnaceBlockEntity extends LootableContainerBlockEntity implements ExtendedScreenHandlerFactory {
    public static final int INV_SIZE = 2;
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INV_SIZE, ItemStack.EMPTY);

    public ExoFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(PesartBEInitializer.EF_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        NbtList inventoryTag = new NbtList();
        int i = 0;
        Iterator<ItemStack> iter = this.inventory.iterator();
        while (iter.hasNext()) {
            ItemStack stack = iter.next();
            NbtCompound itemTag = new NbtCompound();
            stack.writeNbt(itemTag);
            NbtCompound mainTag = new NbtCompound();
            mainTag.put("Item", itemTag);
            mainTag.putShort("Slot", (short) i);
            inventoryTag.add(mainTag);
            i++;
        }
        nbt.put("Inventory", inventoryTag);
        nbt.putInt("RemainingFuel", this.currentFuel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        NbtList inventoryTag = nbt.getList("Inventory", NbtElement.COMPOUND_TYPE);
        Iterator<NbtElement> iter = inventoryTag.iterator();
        while (iter.hasNext()) {
            NbtCompound main = (NbtCompound) iter.next();
            ItemStack stack = ItemStack.fromNbt(main.getCompound("Item"));
            this.inventory.set(main.getShort("Slot"), stack);
        }
        this.currentFuel = nbt.getInt("RemainingFuel");
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> var1) {
        this.inventory = var1;
    }

    @Override
    protected Text getContainerName() {
        return getCachedState().getBlock().getName();
    }

    private ExoFurnaceGui gui;

    @Override
    protected ScreenHandler createScreenHandler(int var1, PlayerInventory var2) {
        this.gui = new ExoFurnaceGui(this.pos, var1, var2, ScreenHandlerContext.create(world, pos));
        return this.gui;
    }

    public int currentFuel = 0;

    private boolean hasFuel() {
        return this.currentFuel > 0;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (!world.isClient) {
            ItemStack fuelItem = this.inventory.get(0);
            if (!fuelItem.isEmpty() && currentFuel == 0) {
                Integer fuelAmount = FuelRegistry.INSTANCE.get(fuelItem.getItem());
                if (fuelAmount != null) {
                    fuelItem.setCount(fuelItem.getCount() - 1);
                    this.inventory.set(0, fuelItem);
                    this.currentFuel += fuelAmount;
                    world.updateListeners(pos, state, state, 2);
                }
            }
            if (currentFuel > 0) {
                world.updateListeners(pos, state, state, 2);
                currentFuel--;
            }
        }
        if (this.hasFuel()) {
            Random random = ThreadLocalRandom.current();
            world.addParticle(ParticleTypes.SMOKE,
                    (double) pos.getX() + 0.5
                            + random.nextDouble() / 4.0 * (double) (random.nextBoolean() ? 1 : -1),
                    (double) pos.getY() + 0.4,
                    (double) pos.getZ() + 0.5
                            + random.nextDouble() / 4.0 * (double) (random.nextBoolean() ? 1 : -1),
                    0.0, 0.005, 0.0);
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = new NbtCompound();
        this.writeNbt(nbt);
        return nbt;
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
