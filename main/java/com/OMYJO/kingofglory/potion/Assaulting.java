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
	private static final UUID ASSAULTING_MODIFIER = UUID.randomUUID();
	public Assaulting()
	{
		super(EffectType.BENEFICIAL, 0x000000);
		setRegistryName("assaulting");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, ASSAULTING_MODIFIER.toString(),(double)0.12F, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
	{
		switch (amplifier)
		{
			case 2:
				super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
				break;
		}
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
