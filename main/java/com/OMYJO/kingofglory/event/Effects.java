package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.potion.ChasingSun;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@net.minecraftforge.registries.ObjectHolder("kingofglory")
public class Effects
{
	@ObjectHolder("chasing_sun")
	public static ChasingSun CHASING_SUN;


	@SubscribeEvent
	public static void onEffectsRegistry(final RegistryEvent.Register<Effect> event)
	{
		event.getRegistry().register(new ChasingSun());
	}
}
