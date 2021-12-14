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
package com.exomatic.pesart.blocks;

import java.util.List;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;

public class ElevatorBlock extends Block {
    private int elevatorRange;
    public ElevatorBlock(int elevatorRange) {
        super(FabricBlockSettings.of(Material.METAL));
        this.elevatorRange = elevatorRange;
    }
    @Override
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        tooltip.add(new TranslatableText("tooltip.pesart." + this.getTranslationKey()).formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("tooltip.pesart." + this.getTranslationKey() + ".flavour").formatted(Formatting.ITALIC, Formatting.DARK_GRAY));
    }
    private void playSound(PlayerEntity player, boolean up) {
        if (up) {
            player.playSound(SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.25F, 1);
        } else {
            player.playSound(SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.25F, 1);
        }
    }
    public void goUp(PlayerEntity player, BlockState state, BlockPos pos) {
        player.setFrozenTicks(2);
        int i;
        BlockPos pos2 = null;
        boolean found = false;
        for (i = 1; i < this.elevatorRange + 1; i++) {
            pos2 = pos.add(0, i, 0);
            BlockState potentialElevator = player.world.getBlockState(pos2);
            if (potentialElevator.getBlock() instanceof ElevatorBlock) {
                found = true;
                break;
            }
        }
        if (!found) {
            return;
        }
        BlockPos pos3;
        for (i = 1; i < 3; i++) {
            pos3 = pos2.add(0, i, 0);
            BlockState checkBlock = player.world.getBlockState(pos3);
            if (!checkBlock.isAir()) {
                return;
            }
        }
        player.teleport(pos2.getX() + 0.5, pos2.getY() + 1, pos2.getZ() + 0.5);
        player.addVelocity(0, 0.25, 0);
        player.velocityModified = true;
        playSound(player, true);
    }
    public void goDown(PlayerEntity player, BlockState state, BlockPos pos) {
        int i;
        BlockPos pos2 = null;
        boolean found = false;
        for (i = 1; i < this.elevatorRange + 1; i++) {
            pos2 = pos.subtract(new Vec3i(0, i, 0));
            BlockState potentialElevator = player.world.getBlockState(pos2);
            if (potentialElevator.getBlock() instanceof ElevatorBlock) {
                found = true;
                break;
            }
        }
        if (!found) {
            return;
        }
        BlockPos pos3;
        for (i = 1; i < 3; i++) {
            pos3 = pos2.add(0, i, 0);
            BlockState checkBlock = player.world.getBlockState(pos3);
            if (!checkBlock.isAir()) {
                return;
            }
        }
        player.teleport(pos2.getX() + 0.5, pos2.getY() + 1, pos2.getZ() + 0.5);
        playSound(player, false);
    }
}
