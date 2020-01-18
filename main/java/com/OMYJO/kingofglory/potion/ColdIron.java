package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class ColdIron extends Effect implements KingOfEffect
{
	private static final UUID CRUSHING_ICE_MODIFIER = UUID.randomUUID();
	public ColdIron()
	{
		super(EffectType.HARMFUL, 0x000000);
		setRegistryName("cold_iron");
		addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, CRUSHING_ICE_MODIFIER.toString(),(double)-0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL);
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, CRUSHING_ICE_MODIFIER.toString(),(double)-0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		if(modifier.getName() == SharedMonsterAttributes.MOVEMENT_SPEED.getName())
		{
			return modifier.getAmount() * (double)(amplifier);
		}
		else if(modifier.getName() == SharedMonsterAttributes.ATTACK_SPEED.getName())
		{
			return modifier.getAmount() * (double)(amplifier + 1);
		}
		return modifier.getAmount() * (double)(1);
	}
}
