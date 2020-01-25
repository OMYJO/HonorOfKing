package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class CrushingIce extends Effect implements KingOfEffect
{
	private static final UUID CRUSHING_ICE_MODIFIER = UUID.randomUUID();
	public CrushingIce()
	{
		super(EffectType.HARMFUL, 0xffffff);
		setRegistryName("crushing_ice");
		addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, CRUSHING_ICE_MODIFIER.toString(),(double)-0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL);
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, CRUSHING_ICE_MODIFIER.toString(),(double)-0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
