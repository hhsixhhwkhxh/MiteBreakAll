package hhsixhhwkhxh.mite.screen;

import hhsixhhwkhxh.mite.block.MiteAnvilBlock;
import hhsixhhwkhxh.mite.blockentity.MiteAnvilBlockEntity;
import hhsixhhwkhxh.mite.menu.MiteAnvilMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;


public class MiteAnvilScreen extends ItemCombinerScreen<MiteAnvilMenu> {

    private static final int greenColor = -8323296;
    private static final int redColor = -40864;

    private static final ResourceLocation TEXT_FIELD_SPRITE = ResourceLocation.withDefaultNamespace("container/anvil/text_field");
    private static final ResourceLocation TEXT_FIELD_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace("container/anvil/text_field_disabled");
    private static final ResourceLocation ERROR_SPRITE = ResourceLocation.withDefaultNamespace("container/anvil/error");
    private static final ResourceLocation ANVIL_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/container/anvil.png");
    private static final Component TOO_EXPENSIVE_TEXT = Component.translatable("container.repair.expensive").withColor(redColor);
    private static final Component REQUIRE_HIGHER_LEVEL_ANVIL_TEXT = Component.translatable("container.repair.higher_anvil").withColor(redColor);
    private static final Component ITEM_NOT_SUPPORTED_TEXT = Component.translatable("container.repair.unsupported_item").withColor(redColor);


    private EditBox name;
    private final Player player;

    public MiteAnvilScreen(MiteAnvilMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, ANVIL_LOCATION);
        this.player = playerInventory.player;
        this.titleLabelX = 60;
    }

    @Override
    protected void subInit() {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.name = new EditBox(this.font, i + 62, j + 24, 103, 12, Component.translatable("container.repair"));
        this.name.setCanLoseFocus(false);
        this.name.setTextColor(-1);
        this.name.setTextColorUneditable(-1);
        this.name.setBordered(false);
        this.name.setMaxLength(50);
        this.name.setResponder(this::onNameChanged);
        this.name.setValue("");
        this.addRenderableWidget(this.name);
        this.name.setEditable(this.menu.getSlot(0).hasItem());
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.minecraft.player.experienceDisplayStartTick = this.minecraft.player.tickCount;
    }

    @Override
    protected void setInitialFocus() {
        this.setInitialFocus(this.name);
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        String s = this.name.getValue();
        this.init(minecraft, width, height);
        this.name.setValue(s);
    }

    /**
     * Called when a keyboard key is pressed within the GUI element.
     * <p>
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param keyCode   the key code of the pressed key.
     * @param scanCode  the scan code of the pressed key.
     * @param modifiers the keyboard modifiers.
     */
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.player.closeContainer();
        }

        return !this.name.keyPressed(keyCode, scanCode, modifiers) && !this.name.canConsumeInput() ? super.keyPressed(keyCode, scanCode, modifiers) : true;
    }

    private void onNameChanged(String name) {
        Slot slot = this.menu.getSlot(0);
        if (slot.hasItem()) {
            String s = name;
            if (!slot.getItem().has(DataComponents.CUSTOM_NAME) && name.equals(slot.getItem().getHoverName().getString())) {
                s = "";
            }

            if (this.menu.setItemName(s)) {
                this.minecraft.player.connection.send(new ServerboundRenameItemPacket(s));
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);

        ContainerData dataAccess = this.menu.getDataAccess();

        if(dataAccess.get(MiteAnvilBlockEntity.VARIANTS) == MiteAnvilBlock.AnvilVariant.ADAMANTIUM.ordinal()){
            return;
        }

        int cost = this.menu.getCost();

        Component component;

        if(cost>=0){
            MutableComponent mutableComponent = Component.literal(String.valueOf(dataAccess.get(MiteAnvilBlockEntity.DURABILITY))).withColor(greenColor);

            if(cost>0){
                mutableComponent = mutableComponent.append(
                        Component.literal("-"+cost).withColor(redColor)
                );
            }

            mutableComponent = mutableComponent.append(Component.literal("/"+dataAccess.get(MiteAnvilBlockEntity.MAX_DAMAGE)).withColor(greenColor));
            component = mutableComponent;
        }else{
            component = switch (cost){
                case MiteAnvilMenu.COST_TOO_EXPENSIVE -> TOO_EXPENSIVE_TEXT;
                case MiteAnvilMenu.COST_REQUIRE_HIGHER_LEVEL_ANVIL -> REQUIRE_HIGHER_LEVEL_ANVIL_TEXT;
                case MiteAnvilMenu.COST_ITEM_NOT_SUPPORTED -> ITEM_NOT_SUPPORTED_TEXT;
                default -> throw new IllegalStateException("Unexpected value: " + cost);
            };
        }

        int x = this.imageWidth - 8 - this.font.width(component) - 2;
        guiGraphics.fill(x - 2, 67, this.imageWidth - 8, 79, 1325400064);
        guiGraphics.drawString(this.font, component, x, 69,-1,false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
        guiGraphics.blitSprite(
            RenderPipelines.GUI_TEXTURED,
            this.menu.getSlot(0).hasItem() ? TEXT_FIELD_SPRITE : TEXT_FIELD_DISABLED_SPRITE,
            this.leftPos + 59,
            this.topPos + 20,
            110,
            16
        );
    }

    @Override
    protected void renderErrorIcon(GuiGraphics guiGraphics, int x, int y) {
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, ERROR_SPRITE, x + 99, y + 45, 28, 21);
        }
    }

    /**
     * Sends the contents of an inventory slot to the client-side Container. This doesn't have to match the actual contents of that slot.
     */
    @Override
    public void slotChanged(AbstractContainerMenu containerToSend, int slotInd, ItemStack stack) {
        if (slotInd == 0) {
            this.name.setValue(stack.isEmpty() ? "" : stack.getHoverName().getString());
            this.name.setEditable(!stack.isEmpty());
            this.setFocused(this.name);
        }
    }
}
