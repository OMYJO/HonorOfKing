package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.item.armor.ClothJerkin;
import com.OMYJO.kingofglory.item.bow.CloudPiercingBow;
import com.OMYJO.kingofglory.item.bow.DayBreaker;
import com.OMYJO.kingofglory.item.bow.TwilightBow;
import com.OMYJO.kingofglory.item.weapon.*;
import net.minecraft.inventory.EquipmentSlotType;
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
		event.getRegistry().register(new IronSword());
		event.getRegistry().register(new Dagger());
		event.getRegistry().register(new PugilistsGauntlet());
		event.getRegistry().register(new BloodDrinker());
		event.getRegistry().register(new ThunderclapBrand());
		event.getRegistry().register(new RagingClaw());
		event.getRegistry().register(new SunglowStriker());
		event.getRegistry().register(new SwiftStrikeLance());
		event.getRegistry().register(new TwinBladesOfDestruction());
		event.getRegistry().register(new StormSword());
		event.getRegistry().register(new Meteor());
		event.getRegistry().register(new BloodWeeper());
		event.getRegistry().register(new DivinePunisher());

		event.getRegistry().register(new CloudPiercingBow());
		event.getRegistry().register(new TwilightBow());
		event.getRegistry().register(new DayBreaker());

		event.getRegistry().register(new ClothJerkin(EquipmentSlotType.CHEST,"cloth_jerkin"));
	}
}
