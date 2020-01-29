package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.other.Helper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.IndirectEntityDamageSource;

import java.util.UUID;

public class RedStatuePowerEffect extends KingOfEffectWithImactor implements KingOfEffect
{
	private static final UUID RED_STATUE_POWER_EFFECT_MODIFIER = UUID.randomUUID();
	public RedStatuePowerEffect(LivingEntity entity)
	{
		super(EffectType.HARMFUL,0xffffff,entity);
		setRegistryName("red_statue_power_effect");
		addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, RED_STATUE_POWER_EFFECT_MODIFIER.toString(),(double)-0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
	{
		int damage = (int)(Math.random() * 99) + 30;
		if(entityLivingBaseIn instanceof MonsterEntity)
		{
			damage *= 2;
		}
		entityLivingBaseIn.attackEntityFrom(new IndirectEntityDamageSource("red_statue_power",this.getImactor(),this.getImactor()).setDamageIsAbsolute().setDamageBypassesArmor(), Helper.attackDamage(damage));
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
		return duration % 20 == 1;
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
	{
		return modifier.getAmount() * (double)(1+amplifier);
	}
}
