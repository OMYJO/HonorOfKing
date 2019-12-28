package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.item.CloudPiercingBow;
import com.OMYJO.kingofglory.item.IronSword;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@net.minecraftforge.registries.ObjectHolder("kingofglory")
public class Items
{
	@SubscribeEvent
	public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
	{
		new SharedKingAttributes();
		event.getRegistry().register(new IronSword());
		event.getRegistry().register(new CloudPiercingBow());
	}
}
