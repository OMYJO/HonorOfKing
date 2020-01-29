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
	@ObjectHolder("blood_rage")
	public static BloodRage BLOOD_RAGE;
	@ObjectHolder("marvellous_speed")
	public static MarvellousSpeed MARVELLOUS_SPEED;
	@ObjectHolder("red_statue_power")
	public static RedStatuePower RED_STATUE_POWER;
	@ObjectHolder("blue_statue_power")
	public static BlueStatuePower BLUE_STATUE_POWER;
	@ObjectHolder("tyrants_revenge")
	public static TyrantsRevenge TYRANTS_REVENGE;
	@ObjectHolder("tyrants_revenge2")
	public static TyrantsRevenge2 TYRANTS_REVENGE2;
	@ObjectHolder("overlords_wrath")
	public static OverlordsWrath OVERLORDS_WRATH;
	@ObjectHolder("overlord_vanguard")
	public static OverlordVanguard OVERLORD_VANGUARD;
	@ObjectHolder("storm_awakening")
	public static StormAwakening STORM_AWAKENING;


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
		event.getRegistry().register(new BloodRage());
		event.getRegistry().register(new MarvellousSpeed());
		event.getRegistry().register(new BlueStatuePower());
		event.getRegistry().register(new RedStatuePower());
		event.getRegistry().register(new TyrantsRevenge());
		event.getRegistry().register(new TyrantsRevenge2());
		event.getRegistry().register(new OverlordsWrath());
		event.getRegistry().register(new OverlordVanguard());
		event.getRegistry().register(new StormAwakening());
	}
}
