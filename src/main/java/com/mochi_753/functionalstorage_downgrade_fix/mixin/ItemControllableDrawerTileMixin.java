package com.mochi_753.functionalstorage_downgrade_fix.mixin;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.block.tile.ItemControllableDrawerTile;
import com.buuz135.functionalstorage.item.StorageUpgradeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemControllableDrawerTile.class, remap = false)
public abstract class ItemControllableDrawerTileMixin {
    @Inject(method = "lambda$getStorageUpgradesConstructor$2", at = @At("HEAD"), cancellable = true)
    private void onLambda$getStorageUpgradesConstructor$2(ItemStack stack, Integer integer, CallbackInfoReturnable<Boolean> cir) {
        ItemControllableDrawerTile<?> self = (ItemControllableDrawerTile<?>) (Object) this;

        if (stack.getItem().equals(((RegistryObject<?>) FunctionalStorage.STORAGE_UPGRADES.get(StorageUpgradeItem.StorageTier.IRON)).get())) {
            for (int i = 0; i < self.getStorage().getSlots(); ++i) {
                if (self.getStorage().getStackInSlot(i).getCount() > self.getStorage().getStackInSlot(i).getMaxStackSize()) {
                    cir.setReturnValue(false);
                    cir.cancel();
                }
            }
        }
    }
}
