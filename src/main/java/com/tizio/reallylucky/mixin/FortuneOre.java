package com.tizio.reallylucky.mixin;

import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.loot.function.ApplyBonusLootFunction$OreDrops")
public class FortuneOre {

	@Inject(method="getValue",at=@At("HEAD"), cancellable = true)
	private void maxOre(Random random, int initialCount, int enchantmentLevel, CallbackInfoReturnable<Integer> cir){
		if (enchantmentLevel > 0) cir.setReturnValue(initialCount * (1+enchantmentLevel));
		else cir.setReturnValue(initialCount);
	}

}