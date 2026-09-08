package hhsixhhwkhxh.mite.screen;

import com.google.common.collect.Range;
import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.custom.MeltingCastRecord;
import hhsixhhwkhxh.mite.item.ModItems;
import hhsixhhwkhxh.mite.menu.LargeFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class LargeFurnaceScreen extends AbstractContainerScreen<LargeFurnaceMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"textures/gui/container/furnace_core.png");
    private static final ResourceLocation THERMOMETER_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/thermometer");
    private static final ResourceLocation BURN_PROGRESS_DOWN_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/burn_progress_down");
    private static final ResourceLocation BURN_PROGRESS_RIGHT_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/burn_progress_right");
    private static final ResourceLocation LOCKED_SLOT = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/locked_slot");
    private static final ResourceLocation LIQUID_METAL = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/liquid_metal");

    private boolean hasLockableSlotBeenInitialized = false;

    private Range<Integer> thermometerRangeX,thermometerRangeY;

    private static final HashMap<Integer,ResourceLocation> INGOT_RES_MAP= new HashMap<>();

    static {
        putIngotResMap(ModItems.MERCURY_INGOT,"mercury_ingot");
        putIngotResMap(ModItems.SILVER_INGOT,"silver_ingot");
        putIngotResMap(Items.COPPER_INGOT,"copper_ingot");
        putIngotResMap(Items.GOLD_INGOT,"gold_ingot");
        putIngotResMap(Items.IRON_INGOT,"iron_ingot");
        putIngotResMap(ModItems.HARD_INGOT,"hard_ingot");
        putIngotResMap(ModItems.ANCIENT_METAL_INGOT,"ancient_metal_ingot");
        putIngotResMap(ModItems.MITHRIL_INGOT,"mithril_ingot");
        putIngotResMap(ModItems.ADAMANTIUM_INGOT,"adamantium_ingot");

    }

    private static void putIngotResMap(Item item,String name){
        INGOT_RES_MAP.put(item.getDescriptionId().hashCode(),ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/ingots/"+name));
    }

    private static void putIngotResMap(DeferredItem<Item> item, String name){
        putIngotResMap(item.get(),name);
    }

    public LargeFurnaceScreen(
            LargeFurnaceMenu menu,
        Inventory playerInventory,
        Component title
    ) {
        super(menu, playerInventory, title);
        this.imageWidth = 172;
        this.imageHeight = 235;
    }

    @Override
    public void init() {
        super.init();
        thermometerRangeX = Range.closed(leftPos + 44,leftPos + 44 + 19);
        thermometerRangeY = Range.closed(topPos + 19,topPos + 19 + 59);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        if(hasLockableSlotBeenInitialized){
            return;
        }
        hasLockableSlotBeenInitialized = menu.tryInitLockableSlot();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        //背景
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        //温度计
        int thermometerSpriteHeight =  Mth.ceil(59 * menu.getTemperatureProgress());
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, THERMOMETER_SPRITE, 19, 59, 0, 59-thermometerSpriteHeight, leftPos + 44, topPos + 19 +(59-thermometerSpriteHeight), 19,thermometerSpriteHeight);

        //上锁的格子
        for (int i = 0; i < (4-menu.getCoreQuantity());i++){
            //燃料
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LOCKED_SLOT, 18, 18, 0, 0, leftPos + 19, topPos + 67 - 18*i, 18,18);

            //输入输出
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LOCKED_SLOT, 18, 18, 0, 0, leftPos + 131 - 20*i, topPos + 13, 18,18);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LOCKED_SLOT, 18, 18, 0, 0, leftPos + 131 - 20*i, topPos + 31, 18,18);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LOCKED_SLOT, 18, 18, 0, 0, leftPos + 131 - 20*i, topPos + 67, 18,18);

        }

        //4个进度条
        for (int i = 0;i < menu.getCoreQuantity();i++){
            var progress = menu.getBurnProgress(i);
            int downSpriteHeight = Mth.ceil(15 * progress);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS_DOWN_SPRITE, 11, 15, 0, 0, leftPos + 75 + 20*i, topPos + 51, 11, downSpriteHeight);


        }


    }

    @Override
    public void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderContents(guiGraphics, mouseX, mouseY, partialTick);

        for (int i = 0;i < menu.getCoreQuantity();i++){
            if(!this.menu.isSmeltingRecipe(i)){
                continue;
            }
            var progress = menu.getBurnProgress(i);
            float liquidMetalProgress = 1;
            if(progress<=0.6){
                liquidMetalProgress = progress*(5/3F);
            }
            int x = leftPos + 72 + 20*i;
            int y = topPos + 32;
            int liquidMetalSpriteHeight =  Mth.ceil(16 * liquidMetalProgress);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIQUID_METAL, 16, 16, 0, 16-liquidMetalSpriteHeight, x, y + (16-liquidMetalSpriteHeight), 16,liquidMetalSpriteHeight);

            int hash = menu.getSmeltingOutputName(i);
            if(progress<=0.6||!INGOT_RES_MAP.containsKey(hash)){
                continue;
            }

            float fade = (float) (progress-0.6)*5/2;
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,INGOT_RES_MAP.get(hash),x,y,16,16, fade);

        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        if(this.hoveredSlot!=null&&this.hoveredSlot.hasItem()){
            return;
        }

        if (!this.menu.getCarried().isEmpty()) {
            return;
        }

        if(!thermometerRangeX.contains(mouseX)||!thermometerRangeY.contains(mouseY)){
            return;
        }

        guiGraphics.setTooltipForNextFrame(
                this.font,
                List.of(Component.translatable("container.large_furnace.temperature",Math.round(menu.getTemperature()))),
                Optional.empty(),
                ItemStack.EMPTY,
                mouseX,
                mouseY,
                null
        );
    }

    @Override
    protected @NotNull List<Component> getTooltipFromContainerItem(ItemStack stack) {
        var list = super.getTooltipFromContainerItem(stack);
        if(MeltingCastRecord.MeltingCastMap.containsKey(stack.getItem())){
            list.add(1,MeltingCastRecord.MeltingCastMap.get(stack.getItem()).getMeltingPointToolTip());
        }
        return list;
    }
}
