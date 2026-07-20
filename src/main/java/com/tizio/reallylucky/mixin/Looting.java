package com.tizio.reallylucky.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantedCountIncreaseFunction.class)
public class Looting {

    @Shadow private int limit;
    @Shadow private Holder<Enchantment> enchantment;

    @Inject(method="run",at= @At(value = "HEAD"), cancellable = true)
    private void increase(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir){
        Entity entity = (Entity)context.getParamOrNull(LootContextParams.ATTACKING_ENTITY);
        if (entity instanceof LivingEntity livingentity) {
            int i = EnchantmentHelper.getEnchantmentLevel(this.enchantment, livingentity);
            if (i > 0) {
                stack.grow(i);
                if (this.limit > 0) {
                    stack.limitSize(this.limit);
                }
                cir.setReturnValue(stack);
            }
        }
    }
}