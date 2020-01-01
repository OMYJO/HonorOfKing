package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class AssaultingSpeed extends Effect implements KingOfEffect
{
	private static final UUID ASSAULTING_SPEED_MODIFIER = UUID.randomUUID();
	public AssaultingSpeed()
	{
		super(EffectType.BENEFICIAL, 0x000000);
		setRegistryName("assaulting_speed");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, ASSAULTING_SPEED_MODIFIER.toString(),(double)0.12F, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
