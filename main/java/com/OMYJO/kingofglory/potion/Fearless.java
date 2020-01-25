package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class Fearless extends Effect implements KingOfEffect
{
	private static final UUID FEARLESS_MODIFIER = UUID.randomUUID();
	public Fearless()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("fearless");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, FEARLESS_MODIFIER.toString(),(double)0.02F, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(Math.max(amplifier,4)+1);
	}
}
