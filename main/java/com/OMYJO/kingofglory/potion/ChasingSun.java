package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class ChasingSun extends Effect implements KingOfEffect
{
	private static final UUID CHASING_SUN_MODIFIER = UUID.randomUUID();
	public ChasingSun()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("chasing_sun");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED,CHASING_SUN_MODIFIER.toString(),(double)0.4F, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
