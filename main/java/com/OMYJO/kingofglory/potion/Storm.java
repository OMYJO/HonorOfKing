package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class Storm extends Effect implements KingOfEffect
{
	private static UUID STORM_MODIFIER = UUID.randomUUID();
	public Storm()
	{
		super(EffectType.BENEFICIAL, 0x000000);
		setRegistryName("storm");
		addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, STORM_MODIFIER.toString(),(double)0.3F, AttributeModifier.Operation.MULTIPLY_BASE);
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, STORM_MODIFIER.toString(),(double)0.1F, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
