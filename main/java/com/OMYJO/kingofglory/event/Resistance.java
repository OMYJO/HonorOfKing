package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Resistance
{
	@SubscribeEvent
	public static void onPotionAddedEvent(PotionEvent.PotionAddedEvent event)
	{
		if(event.getPotionEffect().getPotion().getEffectType() == EffectType.HARMFUL && !(event.getEntityLiving() instanceof ClientPlayerEntity))
		{
			int n = (int)(event.getPotionEffect().getDuration() * (1 - event.getEntityLiving().getAttributes().getAttributeInstanceByName(SharedKingAttributes.RESISTANCE.getName()).getValue()));
			ObfuscationReflectionHelper.setPrivateValue(EffectInstance.class,event.getPotionEffect(),n,"field_76460_b");
		}
	}
}
