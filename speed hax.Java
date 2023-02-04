package com.example.movement_speed_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class MovementSpeedMod implements ClientModInitializer {

    private static final KeyBinding smootherFpsKeyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.smoother_fps",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_Q,
                    "category.gameplay"
            )
    );

    private boolean smootherFpsEnabled = false;

    @Override
    public void onInitializeClient() {
        KeyBindingRegistry.INSTANCE.addCategory("category.gameplay");
        KeyBindingRegistry.INSTANCE.register(smootherFpsKeyBinding);

        KeyBindingHelper.getBoundKeyConflictContext().addKeyConflictContext(
                smootherFpsKeyBinding,
                new Identifier("key.category.inventory"),
                new Identifier("key.category.creative"),
                new Identifier("key.category.hotbar"),
                new Identifier("key.category.player")
        );

        KeyBindingHelper.getBoundKeyConflictContext().get(smootherFpsKeyBinding).setPressAction(keyBinding -> {
            smootherFpsEnabled = !smootherFpsEnabled;
        });
    }

    public void onTick(MinecraftClient client) {
        if (smootherFpsEnabled) {
            client.player.setSpeed(15f);
        } else {
            client.player.setSpeed(15f);
        }
    }

}


-- press q to work
