//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.robotwar.ardautils.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lotr.client.gui.BasicIngameScreen;
import lotr.client.gui.MiddleEarthFactionsScreen;
import lotr.client.gui.map.MiddleEarthMapScreen;
import lotr.client.gui.widget.button.MiddleEarthMenuButton;
import lotr.common.init.LOTRDimensions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ArdaMasterMenuScreen extends BasicIngameScreen {
    public static final ResourceLocation MENU_ICONS = new ResourceLocation("lotr", "textures/gui/menu_icons.png");
    public static Class<? extends ArdaMenuScreen> lastMenuScreen = null;

    public ArdaMasterMenuScreen() {
        super(new StringTextComponent("MENU"));
    }

    public void init() {
        super.init();
        resetLastMenuScreen();
        int midX = this.width / 2;
        int midY = this.height / 2;
        int buttonGap = 10;
        int buttonSize = 32;
        this.addButton(new MiddleEarthMenuButton(0, 0, MiddleEarthMapScreen.class, new TranslationTextComponent("gui.arda.menu.map"), 3, 77));
        this.addButton(new MiddleEarthMenuButton(0, 0, MiddleEarthFactionsScreen.class, new TranslationTextComponent("gui.arda.menu.factions"), 4, 70));
        this.addButton(new MiddleEarthMenuButton(0, 0, (Class)null, new TranslationTextComponent("gui.arda.menu.playerinfo"), 5, -1));
        this.addButton(new MiddleEarthMenuButton(0, 0, (Class)null, new TranslationTextComponent("gui.arda.menu.fellowships"), 6, -1));
        this.addButton(new MiddleEarthMenuButton(0, 0, (Class)null, new TranslationTextComponent("gui.arda.menu.wiki"), 0, -1));
        this.addButton(new MiddleEarthMenuButton(0, 0, (Class)null, new TranslationTextComponent("gui.arda.menu.settings"), 1, -1));

        List<MiddleEarthMenuButton> menuButtonsToArrange = new ArrayList();
        Iterator var6 = this.buttons.iterator();

        while(var6.hasNext()) {
            Widget widget = (Widget)var6.next();
            if (widget instanceof MiddleEarthMenuButton) {
                MiddleEarthMenuButton menuButton = (MiddleEarthMenuButton)widget;
                menuButton.active = menuButton.canDisplayMenu();
                menuButtonsToArrange.add(menuButton);
            }
        }

        int numButtons = menuButtonsToArrange.size();
        int numTopRowButtons = (numButtons - 1) / 2 + 1;
        int numBtmRowButtons = numButtons - numTopRowButtons;
        int topRowLeft = midX - (numTopRowButtons * buttonSize + (numTopRowButtons - 1) * buttonGap) / 2;
        int btmRowLeft = midX - (numBtmRowButtons * buttonSize + (numBtmRowButtons - 1) * buttonGap) / 2;

        for(int l = 0; l < numButtons; ++l) {
            MiddleEarthMenuButton button = (MiddleEarthMenuButton)menuButtonsToArrange.get(l);
            if (l < numTopRowButtons) {
                button.x = topRowLeft + l * (buttonSize + buttonGap);
                button.y = midY - buttonGap / 2 - buttonSize;
            } else {
                button.x = btmRowLeft + (l - numTopRowButtons) * (buttonSize + buttonGap);
                button.y = midY + buttonGap / 2;
            }
        }

    }

    public static void resetLastMenuScreen() {
        lastMenuScreen = null;
    }

    public void render(MatrixStack matStack, int mouseX, int mouseY, float tick) {
        this.renderBackground(matStack);
        ITextComponent dimensionName = LOTRDimensions.getDisplayName(LOTRDimensions.getCurrentLOTRDimensionOrFallback(this.minecraft.level));
        ITextComponent title = new TranslationTextComponent("gui.arda.menu", new Object[]{dimensionName});
        this.font.drawShadow(matStack, title, (float)(this.width / 2 - this.font.width(title) / 2), (float)(this.height / 2 - 80), 16777215);
        super.render(matStack, mouseX, mouseY, tick);
        Iterator var7 = this.buttons.iterator();

        while(var7.hasNext()) {
            Widget widget = (Widget)var7.next();
            if (widget instanceof MiddleEarthMenuButton) {
                MiddleEarthMenuButton menuButton = (MiddleEarthMenuButton)widget;
                if (menuButton.isHovered() && menuButton.getMessage() != null) {
                    this.renderTooltip(matStack, menuButton.getMessage(), mouseX, mouseY);
                }
            }
        }

    }

    public boolean keyPressed(int key, int scan, int param3) {
        Iterator var4 = this.buttons.iterator();

        while(var4.hasNext()) {
            Widget widget = (Widget)var4.next();
            if (widget instanceof MiddleEarthMenuButton) {
                MiddleEarthMenuButton menuButton = (MiddleEarthMenuButton)widget;
                if (menuButton.visible && menuButton.active && menuButton.menuKeyCode >= 0 && key == menuButton.menuKeyCode) {
                    menuButton.onPress();
                    return true;
                }
            }
        }

        return super.keyPressed(key, scan, param3);
    }

    public static Screen openMenu(PlayerEntity player) {
        if (lastMenuScreen != null) {
            try {
                return (Screen)lastMenuScreen.newInstance();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return new ArdaMasterMenuScreen();
    }
}
