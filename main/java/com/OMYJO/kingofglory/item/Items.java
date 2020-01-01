package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.item.armor.*;
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
		event.getRegistry().register(new SparkForgedDagger());
		event.getRegistry().register(new Destiny());
		event.getRegistry().register(new FrigidLance());
		event.getRegistry().register(new ShadowRipper());
		event.getRegistry().register(new MasterSword());
		event.getRegistry().register(new BrokenStarMace());
		event.getRegistry().register(new EndlessEdge());
		event.getRegistry().register(new Doomsday());
		event.getRegistry().register(new PureSky());
		event.getRegistry().register(new SiegeBreaker());

		event.getRegistry().register(new CloudPiercingBow());
		event.getRegistry().register(new TwilightBow());
		event.getRegistry().register(new DayBreaker());

		event.getRegistry().register(new SparkingSapphire());
		event.getRegistry().register(new SwordOfGlory());
		event.getRegistry().register(new AlchemyTailsman());

		event.getRegistry().register(new ClothJerkin(EquipmentSlotType.CHEST,"cloth_jerkin"));
		event.getRegistry().register(new RevitalizingCrystal());
		event.getRegistry().register(new AntiMageCloak(EquipmentSlotType.CHEST,"anti-mage_cloak"));
		event.getRegistry().register(new ResilientAgate());
		event.getRegistry().register(new ProtectorsVest(EquipmentSlotType.CHEST,"protectors_vest"));
		event.getRegistry().register(new FrostholdTarge());
		event.getRegistry().register(new FusionCore(EquipmentSlotType.CHEST,"fusion_core"));
		event.getRegistry().register(new BeltOfMight(EquipmentSlotType.CHEST,"belt_of_might"));
		event.getRegistry().register(new ClandestineCape(EquipmentSlotType.CHEST,"clandestine_cape"));
		event.getRegistry().register(new RedLotusCape(EquipmentSlotType.CHEST,"red_lotus_cape"));
		event.getRegistry().register(new Spikemail(EquipmentSlotType.LEGS,"spikemail"));
		event.getRegistry().register(new CuirassOfSavagery(EquipmentSlotType.HEAD,"cuirass_of_savagery"));
		event.getRegistry().register(new FrostscarsEmbrace());
		event.getRegistry().register(new OverloadsPlatemail(EquipmentSlotType.LEGS,"overloads_platemail"));
		event.getRegistry().register(new SagesSanctuary());
		event.getRegistry().register(new GlacialBuckler(EquipmentSlotType.LEGS,"glacial_buckler"));
		event.getRegistry().register(new EyeOfThePhoenix(EquipmentSlotType.HEAD,"eye_of_the_phoenix"));
		event.getRegistry().register(new SuccubusCloak(EquipmentSlotType.CHEST,"succubus_cloak"));
		event.getRegistry().register(new OminousPremonition(EquipmentSlotType.HEAD,"ominous_premonition"));

		event.getRegistry().register(new BootsOfDivineSpeed());
		event.getRegistry().register(new BootsOfDeftness());
		event.getRegistry().register(new BootsOfDexterity());
		event.getRegistry().register(new BootsOfTheArcane());
		event.getRegistry().register(new BootsOfTranquility());
		event.getRegistry().register(new BootsOfResistance());
		event.getRegistry().register(new BootsOfForitude());
	}
}
