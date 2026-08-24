package hhsixhhwkhxh.mite.custom;

public enum MaterialLevelType {
    GOLD_COPPER_FAMILY(1),
    IRON_STEEL_FAMILY(2),
    ANCIENT_HARDENED_FAMILY(3),
    MITHRIL(4),ADAMANTIUM(5);

    public final int level;
    MaterialLevelType(int level) {
        this.level = level;
    }
}
