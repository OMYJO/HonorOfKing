package com.OMYJO.kingofglory.potion;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class Ancestry extends Effect implements KingOfEffect
{
	public Ancestry()
	{
		super(EffectType.BENEFICIAL, 0x000000);
		setRegistryName("ancestry");
	}
}
