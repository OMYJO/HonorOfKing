package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class Dispelling extends Effect implements KingOfEffect
{
	public Dispelling()
	{
		super(EffectType.BENEFICIAL, 0x000000);
		setRegistryName("dispelling");
	}
}
