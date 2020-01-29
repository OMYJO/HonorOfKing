package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class OverlordsWrath extends Effect implements KingOfEffect
{
	public OverlordsWrath()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("overlords_wrath");
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
	{
		if(entityLivingBaseIn instanceof PlayerEntity)
		{
			entityLivingBaseIn.heal(0.01F * entityLivingBaseIn.getMaxHealth());
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
		return duration % 60 == 0;
	}
}
