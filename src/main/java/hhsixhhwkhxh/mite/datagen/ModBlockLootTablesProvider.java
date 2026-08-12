package hhsixhhwkhxh.mite.datagen;


import hhsixhhwkhxh.mite.block.ModBlocks;
import hhsixhhwkhxh.mite.item.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    public ModBlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SILVER_ORE.get());
        this.dropSelf(ModBlocks.HARD_ORE.get());
        this.dropSelf(ModBlocks.MERCURY_ORE.get());
        this.dropSelf(ModBlocks.MITHRIL_ORE.get());
        this.dropSelf(ModBlocks.ADAMANTIUM_ORE.get());
        this.dropSelf(ModBlocks.TIN_ORE.get());

        this.dropSelf(ModBlocks.FLINT_CRAFTING_TABLE.get());

        this.add(
                ModBlocks.STRAWBERRY_BUSH.get(),
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH.get())
                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                                )
                                                .add(LootItem.lootTableItem(ModItems.STRAWBERRIES.get()))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(registries.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH.get())
                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 1))
                                                )
                                                .add(LootItem.lootTableItem(ModItems.STRAWBERRIES.get()))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(registries.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );

        createGravelBlockLoot();
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>(
                ModBlocks.BLOCKS.getEntries().stream().map(Holder::value).toList()
        );
        blocks.add(Blocks.GRAVEL);
        return blocks;
    }



    private void createGravelBlockLoot() {
        this.add(Blocks.GRAVEL,
                block ->
                        createSilkTouchDispatchTable(Blocks.GRAVEL,
                                AlternativesEntry.alternatives(
                                        LootItem.lootTableItem(Items.GRAVEL).when(BonusLevelTableCondition.bonusLevelFlatChance(registries.getOrThrow(Enchantments.FORTUNE), 0.75F, 0.7F, 0.625F, 0.5F)),
                                        LootItem.lootTableItem(ModItems.OBSIDIAN_SHARD).when(LootItemRandomChanceCondition.randomChance(0.12F)),
                                        AlternativesEntry.alternatives(
                                                LootItem.lootTableItem(Items.FLINT).when(LootItemRandomChanceCondition.randomChance(0.0625F)),
                                                LootItem.lootTableItem(ModItems.FLINT_SHARD)
                                        ).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.COPPER_NUGGET).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.SILVER_NUGGET).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(Items.GOLD_NUGGET).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.HARD_NUGGET).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.EMERALD_SHARD).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.DIAMOND_SHARD).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.MITHRIL_NUGGET).when(LootItemRandomChanceCondition.randomChance(0.666F)),
                                        LootItem.lootTableItem(ModItems.ADAMANTIUM_NUGGET)
                                )
                        )
        );
    }


    protected LootItemCondition.Builder hasEnchantment(ResourceKey<Enchantment> enchantment,int level) {
        return MatchTool.toolMatches(
                ItemPredicate.Builder.item()
                        .withComponents(
                                DataComponentMatchers.Builder.components()
                                        .partial(
                                                DataComponentPredicates.ENCHANTMENTS,
                                                EnchantmentsPredicate.enchantments(
                                                        List.of(
                                                                new EnchantmentPredicate(
                                                                        this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment), MinMaxBounds.Ints.atLeast(level)
                                                                )
                                                        )
                                                )
                                        )
                                        .build()
                        )
        );
    }
}
