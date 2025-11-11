package org.polyfrost.numericalenchantments.config;

import org.polyfrost.numericalenchantments.NumericalEnchantments;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

public class NumericalEnchantmentsConfig extends Config {
    public NumericalEnchantmentsConfig() {
        super(NumericalEnchantments.ID + ".json", NumericalEnchantments.NAME, Category.QOL);

        loadFrom("patcher.toml");
    }

    @Switch(
        title = "Numerical Enchantments",
        description = "Use readable numbers instead of Roman numerals on enchants."
    )
    public boolean numericalEnchants;

    @Switch(
        title = "Translate Unknown Roman Numerals",
        description = "Generate Roman numeral from enchantment and potion levels instead of using language file."
    )
    public boolean betterRomanNumerals = true;
}
