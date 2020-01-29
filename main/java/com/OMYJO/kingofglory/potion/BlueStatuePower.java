package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class BlueStatuePower extends Effect implements KingOfEffect
{
	private static final UUID BLUE_STATUE_POWER_MODIFIER = UUID.randomUUID();
	public BlueStatuePower()
	{
		super(EffectType.BENEFICIAL, 0x0066cc);
		setRegistryName("blue_statue_power");
		addAttributesModifier(SharedKingAttributes.COOL_DOWN_REDUCTION, BLUE_STATUE_POWER_MODIFIER.toString(),0.2, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
	{
		if(entityLivingBaseIn instanceof PlayerEntity)
		{
			ItemStack stack = entityLivingBaseIn.getHeldItemMainhand();
			if(stack.getItem() instanceof KingOfItem)
			{
				stack.setDamage(stack.getDamage() - (int)(stack.getMaxDamage() * 0.02));
			}
		}
	}

	/**
	 * checks if Potion effect is ready to be applied this tick.
	 *
	 * @param duration
	 * @param amplifier
	 */
	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return duration % 20 == 0;
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1);
	}
}
