package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class Cripple extends Effect implements KingOfEffect
{
	private static UUID CRIPPLE_MODIFIER = UUID.randomUUID();
	public Cripple()
	{
		super(EffectType.HARMFUL, 0x000000);
		setRegistryName("cripple");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED,CRIPPLE_MODIFIER.toString(),(double)-0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
