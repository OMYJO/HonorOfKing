package com.OMYJO.kingofglory.event;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@net.minecraftforge.registries.ObjectHolder("kingofglory")
public class Potions
{
	@SubscribeEvent
	public static void onPotionsRegistry(final RegistryEvent.Register<Potion> event)
	{

	}
}
