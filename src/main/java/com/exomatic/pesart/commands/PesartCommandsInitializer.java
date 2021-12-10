package com.exomatic.pesart.commands;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.DefaultPosArgument;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class PesartCommandsInitializer {
    public static void setup() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("dbg_explosion").then(CommandManager.argument("position", BlockPosArgumentType.blockPos()).then(CommandManager.argument("power", IntegerArgumentType.integer()).executes((ctx) -> {
                try {
                    BlockPos position = ctx.getArgument("position", DefaultPosArgument.class).toAbsoluteBlockPos(ctx.getSource());
                    int power = ctx.getArgument("power", Integer.class);
                    ctx.getSource().sendFeedback(Text.of("Creating an explosion of power " + power + " at " + position.toShortString()), true);
                    ctx.getSource().getWorld().createExplosion(null, position.getX(), position.getY(), position.getZ(), power, DestructionType.BREAK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
                return 1;
            }))));
        });
    }
}
