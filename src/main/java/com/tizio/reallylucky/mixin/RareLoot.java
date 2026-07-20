package com.tizio.reallylucky.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RandomChanceWithEnchantedBonusLootCondition.class)
public class RareLoot {

    @Shadow private RegistryEntry<Enchantment> enchantment;

    @Inject(method="test(Lnet/minecraft/loot/context/LootContext;)Z",at=@At("HEAD"), cancellable = true)
    private void boost(LootContext lootContext, CallbackInfoReturnable<Boolean> cir){
        Entity entity = (Entity)lootContext.get(LootContextParameters.ATTACKING_ENTITY);
        int i = entity instanceof LivingEntity livingEntity ? EnchantmentHelper.getEquipmentLevel(this.enchantment, livingEntity) : 0;
        if (i>0){
            cir.setReturnValue(lootContext.getRandom().nextFloat()<i*0.334f);
        }
    }

}