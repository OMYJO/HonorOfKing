package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class Assaulting extends Effect implements KingOfEffect
{
	public Assaulting()
	{
		super(EffectType.BENEFICIAL, 0x000000);
		setRegistryName("assaulting");
	}
}
