package dev.microcontrollers.numericalenchantments.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class NumericalEnchantmentsConfig {
    public static final ConfigClassHandler<NumericalEnchantmentsConfig> CONFIG = ConfigClassHandler.createBuilder(NumericalEnchantmentsConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("numericalenchantments.json"))
                    .build())
            .build();

    @SerialEntry public boolean enabled = true;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Component.translatable("numerical-enchantments.numerical-enchantments"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("numerical-enchantments.numerical-enchantments"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.translatable("numerical-enchantments.use-arabic-numbers"))
                                .description(OptionDescription.of(Component.translatable("numerical-enchantments.use-arabic-numbers.description")))
                                .binding(true, () -> config.enabled, newVal -> config.enabled = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
