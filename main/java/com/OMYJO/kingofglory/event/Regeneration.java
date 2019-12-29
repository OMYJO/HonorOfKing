package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Regeneration
{
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity playerEntity = event.player;
		long time = playerEntity.getEntityWorld().getDayTime();
		if (time % 100 == 0)
		{
			playerEntity.heal((float) playerEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.HP_PER_5_SECOND.getName()).getValue());
		}
	}

	@SubscribeEvent
	public void onLivingHeal(LivingHealEvent event)
	{
		//制裁
	}
}
