package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.OMYJO.kingofglory.potion.Effects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Regeneration
{
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity playerEntity = event.player;
		long time = playerEntity.getEntityWorld().getDayTime();
		if (time % 100 == 0)
		{
			playerEntity.heal((float) playerEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.HP_PER_5_SECOND.getName()).getValue());
		}
	}

	@SubscribeEvent
	public static void onLivingHeal(LivingHealEvent event)
	{
		//制裁
		if(event.getEntityLiving().isPotionActive(Effects.SEVERE_WOUND))
		{
			event.setAmount(event.getAmount()/2);
		}
	}
}
