//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.robotwar.ardautils.gui;

import lotr.client.LOTRKeyHandler;
import lotr.client.gui.BasicIngameScreen;
import lotr.client.gui.widget.button.LeftRightButton;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class ArdaMenuScreen extends BasicIngameScreen {
    public int xSize = 200;
    public int ySize = 256;
    public int guiLeft;
    public int guiTop;
    protected Button buttonMenuReturn;

    public ArdaMenuScreen(ITextComponent titleComponent) {
        super(titleComponent);
    }

    public void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int buttonW = 100;
        int buttonH = 20;
        int buttonGap = 40;
        this.buttonMenuReturn = (Button)this.addButton(new LeftRightButton(0, this.guiTop + (this.ySize + buttonH) / 4, buttonW, buttonH, true, new TranslationTextComponent("gui.lotr.menu.return"), (b) -> {
            this.minecraft.setScreen(new ArdaMasterMenuScreen());
        }));
        this.buttonMenuReturn.x = Math.max(0, this.guiLeft - buttonGap - buttonW);
    }

    public boolean keyPressed(int key, int scan, int param3) {
        if (LOTRKeyHandler.KEY_BIND_MENU.matches(key, scan)) {
            this.minecraft.setScreen(new ArdaMasterMenuScreen());
            return true;
        } else {
            return super.keyPressed(key, scan, param3);
        }
    }
}
