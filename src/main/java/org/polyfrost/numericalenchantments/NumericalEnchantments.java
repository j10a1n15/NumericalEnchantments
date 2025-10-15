package org.polyfrost.numericalenchantments;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif

import org.polyfrost.numericalenchantments.config.NumericalEnchantmentsConfig;

//#if FORGE-LIKE
@Mod(modid = NumericalEnchantments.ID, name = NumericalEnchantments.NAME, version = NumericalEnchantments.VERSION)
//#endif
public class NumericalEnchantments
    //#if FABRIC
    //$$ implements ModInitializer
    //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static NumericalEnchantmentsConfig config;

    //#if FABRIC
    //$$ @Override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    public void onInitialize(
        //#if FORGE
        FMLInitializationEvent event
        //#endif
    ) {
        config = new NumericalEnchantmentsConfig();
    }
}
