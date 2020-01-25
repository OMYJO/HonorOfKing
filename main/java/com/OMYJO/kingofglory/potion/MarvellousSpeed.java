package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.other.Helper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class MarvellousSpeed extends Effect implements KingOfEffect
{
	private static final UUID MARVELLOUS_SPEED_MODIFIER = UUID.randomUUID();
	public MarvellousSpeed()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("marvellous_speed");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MARVELLOUS_SPEED_MODIFIER.toString(), Helper.movementSpeed(60), AttributeModifier.Operation.ADDITION);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
