package com.tizio.reallylucky.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantedCountIncreaseLootFunction.class)
public class Looting {

    @Shadow private int limit;
    @Shadow private RegistryEntry<Enchantment> enchantment;

    @Inject(method="process",at= @At(value = "HEAD"), cancellable = true)
    private void increase(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir){
        Entity entity = (Entity)context.get(LootContextParameters.ATTACKING_ENTITY);
        if (entity instanceof LivingEntity livingEntity) {
            int i = EnchantmentHelper.getEquipmentLevel(this.enchantment, livingEntity);
            if (i > 0) {
                stack.increment(i);
                if (this.limit > 0) {
                    stack.capCount(this.limit);
                }
                cir.setReturnValue(stack);
            }
        }
    }
}