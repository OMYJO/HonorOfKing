package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.UUID;

public class OverlordVanguard extends Effect implements KingOfEffect
{
	public OverlordVanguard()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("overlord_vanguard");
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
	{
		if(entityLivingBaseIn instanceof PlayerEntity)
		{
			EntityType entitytype = EntityType.IRON_GOLEM;
			World world = entityLivingBaseIn.getEntityWorld();
			entitytype.spawn(world, null, (PlayerEntity) entityLivingBaseIn, entityLivingBaseIn.getPosition(), SpawnReason.EVENT, true, false);
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
		return duration % 600 == 1;
	}
}
