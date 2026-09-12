package hhsixhhwkhxh.mite;

import hhsixhhwkhxh.mite.accessor.PlayerMixinAccessor;
import hhsixhhwkhxh.mite.custom.ModFoodData;
import hhsixhhwkhxh.mite.custom.PlayerWaterData;
import hhsixhhwkhxh.mite.item.ModItems;
import hhsixhhwkhxh.mite.packet.ClientboundSetVitalStatMaxValuePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class Utils {
    public static int getVitalStatMaxValue(int level){
        return Mth.clamp((level/5)*2+6,6,20);
    }

    public static void setVitalStatMaxValue(Player player, int value){
        PlayerWaterData waterData = ((PlayerMixinAccessor) player).getWaterData();
        waterData.setMaxWaterLevel(value);

        ModFoodData modFoodData = (ModFoodData) player.getFoodData();
        modFoodData.setMaxFoodLevel(value);

        AttributeInstance maxHealthAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttr != null) {
            maxHealthAttr.setBaseValue(value);
        }
    }

    //server专属
    public static void updateVitalStat(ServerPlayer player){
        int vitalStatMaxValue = Utils.getVitalStatMaxValue(player.experienceLevel);

        Utils.setVitalStatMaxValue(player,vitalStatMaxValue);
        PacketDistributor.sendToPlayer( player, new ClientboundSetVitalStatMaxValuePacket(vitalStatMaxValue));
    }

    public static Optional<BlockPos> loadBlockPos(ValueInput input, String name, BlockPos basePos){
        Optional<Integer> xOpt = input.getInt(name+"_pos_x");
        Optional<Integer> yOpt = input.getInt(name+"_pos_y");
        Optional<Integer> zOpt = input.getInt(name+"_pos_z");

        if(xOpt.isEmpty()|| yOpt.isEmpty()|| zOpt.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(getAbsolutePos(basePos,new BlockPos(xOpt.get(),yOpt.get(),zOpt.get())));
    }

    public static void saveBlockPos(ValueOutput output, String name, BlockPos basePos, @Nullable BlockPos pos){
        if(pos == null){
            return;
        }
        pos = getRelativePos(basePos,pos);
        output.putInt(name+"_pos_x", pos.getX());
        output.putInt(name+"_pos_y", pos.getY());
        output.putInt(name+"_pos_z", pos.getZ());
    }

    public static <T extends Collection<BlockPos>> Optional<T> loadBlockPosCollection(ValueInput input, String name, BlockPos basePos, T posCollection){
        Optional<ValueInput.ValueInputList> valueInputListOpt = input.childrenList(name);
        if(valueInputListOpt.isEmpty()){
            return Optional.empty();
        }

        for (ValueInput child : valueInputListOpt.get()){
            Optional<Integer> xOpt = child.getInt("x");
            Optional<Integer> yOpt = child.getInt("y");
            Optional<Integer> zOpt = child.getInt("z");

            if(xOpt.isEmpty()|| yOpt.isEmpty()|| zOpt.isEmpty()){
                continue;
            }

            posCollection.add(Utils.getAbsolutePos(basePos,new BlockPos(xOpt.get(),yOpt.get(),zOpt.get())));
        }

        if(posCollection.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(posCollection);
    }

    public static <T extends Collection<BlockPos>> void saveBlockPosCollection(ValueOutput output, String name, BlockPos basePos, T posCollection){
        ValueOutput.ValueOutputList listBuilder = output.childrenList(name);
        for (BlockPos absPos : posCollection) {
            ValueOutput child = listBuilder.addChild();
            BlockPos relPos = getRelativePos(basePos,absPos);
            child.putInt("x", relPos.getX());
            child.putInt("y", relPos.getY());
            child.putInt("z", relPos.getZ());
        }
    }

    public static List<IntegerProperty> createBlockPosProperty(String name){
        return List.of(
                IntegerProperty.create(name + "_pos_x", 0,4),
                IntegerProperty.create(name + "_pos_y", 0,4),
                IntegerProperty.create(name + "_pos_z", 0,4)
        );
    }

    public static BlockPos getAbsolutePosFromBlockState(BlockPos basePos, BlockState blockState, List<IntegerProperty> list){
        return basePos.offset(
                blockState.getValue(list.getFirst())-2,
                blockState.getValue(list.get(1))-2,
                blockState.getValue(list.getLast())-2
        );
    }

    public static BlockPos getRelativePos(BlockPos basePos, BlockPos targetPos){
        return new BlockPos(
                targetPos.getX() - basePos.getX(),
                targetPos.getY() - basePos.getY(),
                targetPos.getZ() - basePos.getZ()
        );
    }

    public static BlockPos getAbsolutePos(BlockPos basePos, BlockPos offset){
        return basePos.offset(
                offset.getX(),
                offset.getY(),
                offset.getZ()
        );
    }

    public static BlockState setPropertyBlockPos(BlockState blockState, List<IntegerProperty> properties, BlockPos offset){
        return blockState
                .setValue(properties.get(0), offset.getX()+2)
                .setValue(properties.get(1),offset.getY()+2)
                .setValue(properties.get(2),offset.getZ()+2);
    }

    public static BlockPos[] getHorizontalNeighbourPosList(BlockPos pos){
        return new BlockPos[]{pos.east(),pos.south(),pos.west(),pos.north()};
    }

    public static BlockPos[] getHorizontalCornerPosList(BlockPos pos){
        return new BlockPos[]{pos.offset(-1,0,-1), pos.offset(1,0,1), pos.offset(1,0,-1), pos.offset(-1,0,1)};
    }

    public static Optional<Direction> getRelativeHorizontalDirection(BlockPos basePos, BlockPos neighbourPos){
        if(basePos==null||neighbourPos==null){
            return Optional.empty();
        }
        final Direction[] directions = new Direction[]{Direction.EAST,Direction.SOUTH,Direction.WEST,Direction.NORTH};
        BlockPos[] neighbourPosList = getHorizontalNeighbourPosList(basePos);
        for (int i = 0; i < neighbourPosList.length; i++) {
            if(neighbourPosList[i].equals(neighbourPos)){
                return Optional.of(directions[i]);
            }
        }
        return Optional.empty();
    }

    public static boolean isMould(ItemStack itemStack){
        if(itemStack == null){
            return false;
        }
        if(itemStack.is(ModItems.OBSIDIAN_INGOT_MOULD)){
            return true;
        }
        return false;
    }

    public static void dropItem(ItemStack itemStack, Level level, BlockPos pos){
        Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(level.random, 0.7F);
        ItemEntity itementity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), itemStack);
        itementity.setDefaultPickUpDelay();
        level.addFreshEntity(itementity);
    }

    public static void dropItems(Collection<ItemStack> itemStackCollection, Level level, BlockPos pos){
        itemStackCollection.forEach(itemStack->{
            dropItem(itemStack,level,pos);
        });
    }

    public static void drawBottomToTopProgressBar(GuiGraphics guiGraphics, ResourceLocation spriteResourceLocation, int textureWidth, int textureHeight, int x, int y, float progress){
        if(progress==0){
            return;
        }
        int spriteHeight = Mth.ceil(progress * textureHeight);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, spriteResourceLocation, textureWidth, textureHeight, 0, textureHeight-spriteHeight, x, y + (textureHeight-spriteHeight), textureWidth, spriteHeight);
    }

    public static void drawTopToBottomProgressBar(GuiGraphics guiGraphics, ResourceLocation spriteResourceLocation, int textureWidth, int textureHeight, int x, int y, float progress){
        if(progress==0){
            return;
        }
        int spriteHeight = Mth.ceil(progress * textureHeight);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, spriteResourceLocation, textureWidth, textureHeight, 0, textureHeight-spriteHeight, x, y +(textureHeight-spriteHeight), textureWidth, spriteHeight);
    }

    public static void drawLeftToRightProgressBar(GuiGraphics guiGraphics, ResourceLocation spriteResourceLocation, int textureWidth, int textureHeight, int x, int y, float progress){
        if(progress==0){
            return;
        }
        int spriteWidth = Mth.ceil(progress * textureWidth);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, spriteResourceLocation, textureWidth, textureHeight, 0, 0, x, y, spriteWidth, textureHeight);
    }
}
