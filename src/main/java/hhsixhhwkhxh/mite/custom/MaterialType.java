package hhsixhhwkhxh.mite.custom;

public enum MaterialType {
    WOOD("wood"),FLINT("flint"),STONE("stone"),OBSIDIAN("obsidian"),
    COPPER("copper"), GOLD("gold"), SILVER("silver"), SILVER_COPPER("silver_copper"),BRONZE("bronze"),
    IRON("iron"),RUSTED_IRON("rusted_iron"),HIGH_CARBON_STEEL("high_carbon_steel"),
    ANCIENT_METAL("ancient_metal"), HARD("hard"),
    MITHRIL("mithril"),ADAMANTIUM("adamantium"),

    DIAMOND("diamond"),EMERALD("emerald"),GLASS("glass"),QUARTZ("quartz")

    ;
    private static final MaterialType[] ARMOR_MATERIALS = {
            ADAMANTIUM, ANCIENT_METAL, HARD, MITHRIL, RUSTED_IRON, SILVER, BRONZE, HIGH_CARBON_STEEL, SILVER_COPPER, COPPER
    };

    private static final MaterialType[] TOOL_MATERIALS = {
            WOOD, FLINT, OBSIDIAN, IRON, GOLD, COPPER, BRONZE, SILVER_COPPER, SILVER, RUSTED_IRON, HIGH_CARBON_STEEL, HARD, ANCIENT_METAL, MITHRIL, ADAMANTIUM
    };



    private final String name;
    MaterialType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MaterialType[] getArmorMaterials() {
        return ARMOR_MATERIALS;
    }

    public static MaterialType[] getToolMaterials() {
        return TOOL_MATERIALS;
    }
}
