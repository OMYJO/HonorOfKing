package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class DarkCurtain extends Effect implements KingOfEffect
{
	private static final UUID DARK_CURTAIN_MODIFIER = UUID.randomUUID();
	public DarkCurtain()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("dark_curtain");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, DARK_CURTAIN_MODIFIER.toString(),(double)0.3F, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
