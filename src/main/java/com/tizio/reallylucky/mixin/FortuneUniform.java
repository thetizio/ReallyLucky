package com.tizio.reallylucky.mixin;

import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.storage.loot.functions.ApplyBonusCount$UniformBonusCount")
public class FortuneUniform {

    @Inject(method="calculateNewCount",at=@At("HEAD"), cancellable = true)
    private void maxUni(RandomSource random, int originalCount, int enchantmentLevel, CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(originalCount + enchantmentLevel);
    }

}