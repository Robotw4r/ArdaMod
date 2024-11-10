package net.robotwar.ardautils;

import lotr.client.LOTRKeyHandler;
import lotr.client.gui.MiddleEarthMasterMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.RegistryKey;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.robotwar.ardautils.gui.ArdaMasterMenuScreen;

import static lotr.client.LOTRKeyHandler.KEY_BIND_MENU;

public class ArdaUtilsKeys {

    private static final String KEY_CATEGORY_ARDA_MOD = String.format("key.categories.mod.%s", "ardautils");
    public static final KeyBinding KEY_BIND_ARDA_MENU;

    private static final String keybindName(String name) {
        return String.format("key.%s.%s", "ardautils", name);
    }

    public ArdaUtilsKeys() {
        MinecraftForge.EVENT_BUS.register(this);
        this.registerKeys();
    }

    private void registerKeys() {
        ClientRegistry.registerKeyBinding(KEY_BIND_ARDA_MENU);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int key = event.getKey();
        int scancode = event.getScanCode();
        int action = event.getAction();
        Minecraft mc = Minecraft.getInstance();
        if (KEY_BIND_ARDA_MENU.matches(key, scancode) && mc.screen == null && mc.player != null) {
            Screen menuScreen = ArdaMasterMenuScreen.openMenu(mc.player);
            if (menuScreen != null) {
                mc.setScreen(menuScreen);
            }
        }
    }

    static {
        KEY_BIND_ARDA_MENU = new KeyBinding(keybindName("menu"), 79, KEY_CATEGORY_ARDA_MOD);
    }
}
