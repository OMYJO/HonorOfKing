package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.item.CloudPiercingBow;
import com.OMYJO.kingofglory.item.IronSword;
import com.OMYJO.kingofglory.item.TwilightBow;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
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
		event.getRegistry().register(new CloudPiercingBow());
		event.getRegistry().register(new TwilightBow());
		event.getRegistry().register(new ClothJerkin(EquipmentSlotType.CHEST,"cloth_jerkin"));
	}
}
