package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.AbsorptionEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class BloodRage extends Effect implements KingOfEffect
{
	private static final UUID BLOOD_RAGE_MODIFIER = UUID.randomUUID();
	public BloodRage()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("blood_rage");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, BLOOD_RAGE_MODIFIER.toString(),80, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
	{
		entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() - 0.4F * entityLivingBaseIn.getMaxHealth());
	}

	@Override
	public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
	{
		entityLivingBaseIn.setHealth(entityLivingBaseIn.getHealth() * (1-0.3F));
		entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() + 0.4F * entityLivingBaseIn.getMaxHealth());
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
