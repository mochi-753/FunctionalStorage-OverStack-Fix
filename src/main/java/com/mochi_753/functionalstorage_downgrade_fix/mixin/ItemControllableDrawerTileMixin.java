package com.mochi_753.functionalstorage_downgrade_fix.mixin;

import com.buuz135.functionalstorage.block.tile.ItemControllableDrawerTile;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ItemControllableDrawerTile.class, remap = false)
public abstract class ItemControllableDrawerTileMixin {
    @ModifyConstant(method = "lambda$getStorageUpgradesConstructor$2", constant = @Constant(intValue = 64))
    private int replaceIronUpgradeCheck(int constant, @Local(name = "i") int i) {
        ItemControllableDrawerTile<?> self = (ItemControllableDrawerTile<?>) (Object) this;
        return self.getStorage().getStackInSlot(i).getMaxStackSize();
    }
}
