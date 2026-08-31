package hhsixhhwkhxh.mite.custom;

import java.util.HashSet;
import java.util.Set;

public enum MaterialFamilyType {
    RUBBISH(0),
    GOLD_COPPER_FAMILY(1),
    IRON_STEEL_FAMILY(2),
    ANCIENT_HARDENED_FAMILY(3),
    MITHRIL(4),ADAMANTIUM(5);

    public final int level;
    MaterialFamilyType(int level) {
        this.level = level;
    }

    private static final HashSet<MaterialType> GOLD_COPPER_FAMILIES = new HashSet<>(Set.of(
            MaterialType.GOLD,MaterialType.COPPER,MaterialType.SILVER,MaterialType.SILVER_COPPER,MaterialType.BRONZE
    ));
    private static final HashSet<MaterialType> IRON_STEEL_FAMILIES = new HashSet<>(Set.of(
            MaterialType.IRON,MaterialType.RUSTED_IRON,MaterialType.HIGH_CARBON_STEEL
    ));
    private static final HashSet<MaterialType> ANCIENT_HARDENED_FAMILIES = new HashSet<>(Set.of(
            MaterialType.ANCIENT_METAL,MaterialType.HARD
    ));

    public static MaterialFamilyType getFamily(MaterialType materialType){
        if(materialType == MaterialType.ADAMANTIUM){
            return ADAMANTIUM;
        } else if (materialType == MaterialType.MITHRIL) {
            return MITHRIL;
        }

        if(GOLD_COPPER_FAMILIES.contains(materialType)){
            return GOLD_COPPER_FAMILY;
        }

        if(IRON_STEEL_FAMILIES.contains(materialType)){
            return IRON_STEEL_FAMILY;
        }

        if(ANCIENT_HARDENED_FAMILIES.contains(materialType)){
            return ANCIENT_HARDENED_FAMILY;
        }

        return RUBBISH;
    }

    public int getLevel() {
        return level;
    }

}
