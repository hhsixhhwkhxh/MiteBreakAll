package hhsixhhwkhxh.mite.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public enum RelativePos implements StringRepresentable {
    MINUS_2(-2),
    MINUS_1(-1),
    ZERO(0),
    PLUS_1(1),
    PLUS_2(2),
    NULL(0,true);
    final int offset;
    private static HashMap<Integer,RelativePos> map = new HashMap<>();

    static {
        for (RelativePos value : values()) {
            map.put(value.offset,value);
        }
    }

    RelativePos(int offset){
        this.offset = offset;
    }

    boolean isNull = false;

    RelativePos(int offset,boolean isNull){
        this.offset = offset;
        this.isNull = isNull;
    }
    @Override
    public @NotNull String getSerializedName() {
        return String.valueOf(offset);
    }

    public int getOffset() {
        return offset;
    }

    private static RelativePos safeGet(int offset){
        RelativePos relativePos = map.get(offset);
        if(relativePos!=null){
            return relativePos;
        }
        throw new IllegalStateException("Unexpected offset in RelativePos: "+offset);
    }

//    public static int[] getRelativePosArray(BlockPos basePos, BlockPos targetPos){
//        return new int[]{
//                targetPos.getX() - basePos.getX(),
//                targetPos.getY() - basePos.getY(),
//                targetPos.getZ() - basePos.getZ()
//        };
//    }

    public static RelativePos[] getRelativePos(BlockPos basePos, BlockPos targetPos){
        return new RelativePos[]{
                safeGet(targetPos.getX() - basePos.getX()),
                safeGet(targetPos.getY() - basePos.getY()),
                safeGet(targetPos.getZ() - basePos.getZ())
        };
    }
}