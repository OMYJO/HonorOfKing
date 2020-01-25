package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class SevereWound extends Effect implements KingOfEffect
{
	public SevereWound()
	{
		super(EffectType.HARMFUL, 0xffffff);
		setRegistryName("severe_wound");
	}
}
