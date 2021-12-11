package com.exomatic.pesart.network.packet;

import com.exomatic.pesart.callbacks.PlayerJumpOnBlockCallback;
import com.exopteron.network.ExoPacket;
import com.exopteron.network.Side;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PacketJumpOnBlock extends ExoPacket {
    private BlockPos position;
    public PacketJumpOnBlock() {

    }
    public PacketJumpOnBlock(BlockPos position) {
        this.position = position;
    }
    @Override
    public void read(PacketByteBuf buf) {
        this.position = buf.readBlockPos();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeBlockPos(this.position);
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        if (side == Side.LOGICAL_SERVER) {
            if (this.position.equals(player.getBlockPos().offset(Direction.DOWN))) {
                PlayerJumpOnBlockCallback.EVENT.invoker().run(player, player.world.getBlockState(this.position), this.position);
            }
        }
    }
    
}
