package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class AssaultingSlowness extends Effect implements KingOfEffect
{
	private static final UUID ASSAULTING_SLOWNESS_MODIFIER = UUID.randomUUID();
	public AssaultingSlowness()
	{
		super(EffectType.HARMFUL, 0x000000);
		setRegistryName("assaulting_slowness");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, ASSAULTING_SLOWNESS_MODIFIER.toString(),(double)-0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}


	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(2+amplifier);
	}
}
