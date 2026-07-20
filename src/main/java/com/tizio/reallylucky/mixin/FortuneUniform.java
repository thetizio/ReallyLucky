package com.tizio.reallylucky.mixin;

import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.loot.function.ApplyBonusLootFunction$UniformBonusCount")
public class FortuneUniform {

    @Inject(method="getValue",at=@At("HEAD"), cancellable = true)
    private void maxUni(Random random, int initialCount, int enchantmentLevel, CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(initialCount + enchantmentLevel);
    }

}