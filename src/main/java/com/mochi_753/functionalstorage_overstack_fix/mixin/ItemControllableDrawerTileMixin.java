package com.mochi_753.functionalstorage_overstack_fix.mixin;

import com.buuz135.functionalstorage.block.tile.ItemControllableDrawerTile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemControllableDrawerTile.class)
public class ItemControllableDrawerTileMixin {
    @Redirect(
            method = "onSlotActivated",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;setItemInHand(Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)V"
            )
    )
    private void onSetItemInHand(Player instance, InteractionHand interactionHand, ItemStack itemStack) {
        if (instance.level().isClientSide()) {
            instance.setItemInHand(interactionHand, itemStack);
            return;
        }

        if (itemStack.isEmpty()) {
            instance.setItemInHand(interactionHand, ItemStack.EMPTY);
            return;
        }

        int maxStackSize = itemStack.getMaxStackSize();
        int stackCount = itemStack.getCount() - 1;
        while (stackCount > 0) {
            int addCount = Math.min(maxStackSize, stackCount);
            stackCount -= addCount;

            ItemStack copyStack = itemStack.copyWithCount(addCount);

            if (!instance.getInventory().add(copyStack)) {
                ItemEntity itemEntity = instance.drop(copyStack, false);
                if (itemEntity != null) itemEntity.setTarget(instance.getUUID());
            }
        }
    }
}
