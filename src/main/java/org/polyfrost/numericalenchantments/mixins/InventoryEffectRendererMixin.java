package org.polyfrost.numericalenchantments.mixins;

import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.PotionEffect;
import org.polyfrost.numericalenchantments.NumericalEnchantments;
import org.polyfrost.numericalenchantments.RomanNumerals;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InventoryEffectRenderer.class)
public class InventoryEffectRendererMixin {

    @Unique
    private int numericalEnchantments$potionAmplifierLevel;

    @Redirect(
        method = "drawActivePotionEffects",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/PotionEffect;getAmplifier()I", ordinal = 0)
    )
    private int numericalEnchantments$skipOriginalCode(PotionEffect instance) {
        if (NumericalEnchantments.config.betterRomanNumerals) {
            this.numericalEnchantments$potionAmplifierLevel = instance.getAmplifier();
            return 1;
        }
        return instance.getAmplifier();
    }

    @Redirect(
        method = "drawActivePotionEffects",
        at =
        @At(value = "INVOKE",
            target = "Lnet/minecraft/client/resources/I18n;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
            ordinal = 1)
    )
    private String numericalEnchantments$addRomanNumeral(String translateKey, Object[] parameters) {
        if (NumericalEnchantments.config.betterRomanNumerals) {
            if (this.numericalEnchantments$potionAmplifierLevel > 0) {
                return RomanNumerals.toRoman(this.numericalEnchantments$potionAmplifierLevel + 1);
            }
            return "";
        }
        return I18n.format(translateKey, parameters);
    }

}
