package com.OMYJO.kingofglory.potion;

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
	@ObjectHolder("cripple")
	public static Cripple CRIPPLE;
	@ObjectHolder("chasing_sun")
	public static ChasingSun CHASING_SUN;
	@ObjectHolder("severe_wound")
	public static SevereWound SEVERE_WOUND;
	@ObjectHolder("dark_curtain")
	public static DarkCurtain DARK_CURTAIN;
	@ObjectHolder("crushing_ice")
	public static CrushingIce CRUSHING_ICE;
	@ObjectHolder("storm")
	public static Storm STORM;
	@ObjectHolder("assaulting")
	public static Assaulting ASSAULTING;
	@ObjectHolder("dispelling")
	public static Dispelling DISPELLING;
	@ObjectHolder("cold_iron")
	public static ColdIron COLD_IRON;
	@ObjectHolder("fearless")
	public static Fearless FEARLESS;
	@ObjectHolder("assaulting_slowness")
	public static AssaultingSlowness ASSAULTING_SLOWNESS;
	@ObjectHolder("assaulting_speed")
	public static AssaultingSpeed ASSAULTING_SPEED;
	@ObjectHolder("ice_heart")
	public static IceHeart ICE_HEART;
	@ObjectHolder("ancestry")
	public static Ancestry ANCESTRY;
	@ObjectHolder("blood_rage")
	public static BloodRage BLOOD_RAGE;
	@ObjectHolder("marvellous_speed")
	public static MarvellousSpeed MARVELLOUS_SPEED;

	@SubscribeEvent
	public static void onEffectsRegistry(final RegistryEvent.Register<Effect> event)
	{
		event.getRegistry().register(new Cripple());
		event.getRegistry().register(new SevereWound());
		event.getRegistry().register(new ChasingSun());
		event.getRegistry().register(new DarkCurtain());
		event.getRegistry().register(new CrushingIce());
		event.getRegistry().register(new Storm());
		event.getRegistry().register(new Assaulting());
		event.getRegistry().register(new Dispelling());
		event.getRegistry().register(new ColdIron());
		event.getRegistry().register(new Fearless());
		event.getRegistry().register(new AssaultingSlowness());
		event.getRegistry().register(new AssaultingSpeed());
		event.getRegistry().register(new IceHeart());
		event.getRegistry().register(new Ancestry());
		event.getRegistry().register(new BloodRage());
		event.getRegistry().register(new MarvellousSpeed());
	}
}
