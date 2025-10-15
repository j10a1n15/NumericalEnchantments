package org.polyfrost.numericalenchantments.mixins;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.StatCollector;
import org.polyfrost.numericalenchantments.NumericalEnchantments;
import org.polyfrost.numericalenchantments.RomanNumerals;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow
    public abstract String getName();

    @Inject(method = "getTranslatedName", at = @At("HEAD"), cancellable = true)
    private void numericalEnchantments$modifyRomanNumerals(int level, CallbackInfoReturnable<String> cir) {
        String translation = StatCollector.translateToLocal(this.getName()) + " ";
        if (NumericalEnchantments.config.numericalEnchants) {
            cir.setReturnValue(translation + level);
        } else if (NumericalEnchantments.config.betterRomanNumerals) {
            cir.setReturnValue(translation + RomanNumerals.toRoman(level));
        }
    }
}
