package com.OMYJO.kingofglory.potion;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class RedStatuePower extends Effect implements KingOfEffect
{
	protected RedStatuePower()
	{
		super(EffectType.BENEFICIAL,0xff3333);
		setRegistryName("red_statue_power");
	}
}
