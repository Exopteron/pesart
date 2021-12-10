package com.exomatic.pesart.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlastWallBlock extends Block {
    public BlastWallBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        tooltip.add(new TranslatableText("tooltip.pesart." + this.getName()).formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("tooltip.pesart." + this.getName() + ".flavour").formatted(Formatting.ITALIC, Formatting.DARK_GRAY));
    }
}
