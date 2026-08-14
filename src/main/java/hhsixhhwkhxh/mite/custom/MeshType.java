package hhsixhhwkhxh.mite.custom;

import net.minecraft.util.StringRepresentable;

public enum MeshType implements StringRepresentable {
    EMPTY("empty"),
    LEATHER("leather"),
    STRING("string");

    private final String name;

    private MeshType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }


}
