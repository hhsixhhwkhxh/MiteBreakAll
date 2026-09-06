package hhsixhhwkhxh.mite.screen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.menu.LargeFurnaceMenu;
import hhsixhhwkhxh.mite.menu.ModMenuTypes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.FurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.List;


public class LargeFurnaceScreen extends AbstractContainerScreen<LargeFurnaceMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"textures/gui/container/furnace_core.png");
    private static final ResourceLocation THERMOMETER_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/thermometer");
    private static final ResourceLocation BURN_PROGRESS_DOWN_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/burn_progress_down");
    private static final ResourceLocation BURN_PROGRESS_RIGHT_SPRITE = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/burn_progress_right");
    private static final ResourceLocation LOCKED_SLOT = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/locked_slot");
    private static final ResourceLocation LIQUID_METAL = ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MODID,"container/large_furnace/liquid_metal");


    private boolean hasLockableSlotBeenInitialized = false;


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
            int downSpriteHeight = Mth.ceil(15 * menu.getBurnProgress(i));
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS_DOWN_SPRITE, 11, 15, 0, 0, leftPos + 75 + 20*i, topPos + 51, 11, downSpriteHeight);
        }


    }
}
