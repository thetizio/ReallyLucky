package com.tizio.reallylucky.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootItemRandomChanceWithEnchantedBonusCondition.class)
public class RareLoot {

    @Shadow private Holder<Enchantment> enchantment;

    @Inject(method="test",at=@At("HEAD"), cancellable = true)
    private void boost(LootContext context, CallbackInfoReturnable<Boolean> cir){
        Entity killerEntity = (Entity)context.getOptionalParameter(LootContextParams.ATTACKING_ENTITY);
        int i = killerEntity instanceof LivingEntity livingKiller ? EnchantmentHelper.getEnchantmentLevel(this.enchantment, livingKiller) : 0;
        if (i>0){
            cir.setReturnValue(context.getRandom().nextFloat()<i*0.334f);
        }
    }

}