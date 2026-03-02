package com.mochi_753.functionalstorage_overstack_fix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FunctionalStorageOverStackFix.MOD_ID)
public class FunctionalStorageOverStackFix {
    public static final String MOD_ID = "functionalstorage_overstack_fix";

    @SuppressWarnings("removal")
    public FunctionalStorageOverStackFix() {
        this(FMLJavaModLoadingContext.get());
    }

    public FunctionalStorageOverStackFix(FMLJavaModLoadingContext context) {
    }
}
