package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class IceHeart extends Effect implements KingOfEffect
{
	private static final UUID ICE_HEART_MODIFIER = UUID.randomUUID();
	public IceHeart()
	{
		super(EffectType.HARMFUL, 0xffffff);
		setRegistryName("ice_heart");
		addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, ICE_HEART_MODIFIER.toString(),(double)-0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL);
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, ICE_HEART_MODIFIER.toString(),(double)-0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
