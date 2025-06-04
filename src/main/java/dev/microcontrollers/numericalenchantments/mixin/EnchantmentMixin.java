package dev.microcontrollers.numericalenchantments.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.microcontrollers.numericalenchantments.config.NumericalEnchantmentsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @WrapOperation(method = "getFullname", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/MutableComponent;append(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;", ordinal = 1))
    private static MutableComponent appendArabicNumeral(MutableComponent instance, Component sibling, Operation<MutableComponent> original, @Local(ordinal = 0, argsOnly = true) int level) {
        return NumericalEnchantmentsConfig.CONFIG.instance().enabled ? instance.append(String.valueOf(level)) : original.call(instance, sibling);
    }
}
