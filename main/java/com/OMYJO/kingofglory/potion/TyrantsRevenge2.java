package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class TyrantsRevenge2 extends Effect implements KingOfEffect
{
	private static final UUID TYRANTS_REVENGE_MODIFIER2 = UUID.randomUUID();
	public TyrantsRevenge2()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("tyrants_revenge2");
		addAttributesModifier(SharedKingAttributes.ATTACK_DAMAGE, TYRANTS_REVENGE_MODIFIER2.toString(), 0.12, AttributeModifier.Operation.MULTIPLY_BASE);
		addAttributesModifier(SharedKingAttributes.MAGIC_ATTACK, TYRANTS_REVENGE_MODIFIER2.toString(), 0.12, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
