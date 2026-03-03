package com.mochi_753.functionalstorage_downgrade_fix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FunctionalStorageDowngradeFix.MOD_ID)
public class FunctionalStorageDowngradeFix {
    public static final String MOD_ID = "functionalstorage_downgrade_fix";

    @SuppressWarnings("removal")
    public FunctionalStorageDowngradeFix() {
        this(FMLJavaModLoadingContext.get());
    }

    public FunctionalStorageDowngradeFix(FMLJavaModLoadingContext context) {
    }
}
