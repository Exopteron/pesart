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

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

public class ShelfBlockEntity extends BlockEntity {
    public boolean showAlways = false;
    private ItemStack heldItem = ItemStack.EMPTY;
    public ItemStack getHeldItem() {
        return this.heldItem;
    }
    public void setHeldItem(ItemStack i) {
        this.heldItem = i;
    }
    public ShelfBlockEntity(BlockPos pos, BlockState state) {
        super(PesartBEInitializer.SHELF_BLOCK_ENTITY, pos, state);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound itemTag = new NbtCompound();
        this.heldItem.writeNbt(itemTag);
        nbt.put("HeldItem", itemTag);
        nbt.putBoolean("ShowAlways", this.showAlways);
    }
    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.heldItem = ItemStack.fromNbt(tag.getCompound("HeldItem"));
        this.showAlways = tag.getBoolean("ShowAlways");
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
