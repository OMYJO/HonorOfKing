package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class TyrantsRevenge extends Effect implements KingOfEffect
{
	private static final UUID TYRANTS_REVENGE_MODIFIER = UUID.randomUUID();
	public TyrantsRevenge()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("tyrants_revenge");
		addAttributesModifier(SharedKingAttributes.ATTACK_DAMAGE, TYRANTS_REVENGE_MODIFIER.toString(), Helper.attackDamage(70), AttributeModifier.Operation.ADDITION);
		addAttributesModifier(SharedKingAttributes.MAGIC_ATTACK, TYRANTS_REVENGE_MODIFIER.toString(), 100, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
