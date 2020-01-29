package com.OMYJO.kingofglory.potion;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class StormAwakening extends Effect implements KingOfEffect
{
	public StormAwakening()
	{
		super(EffectType.BENEFICIAL, 0xffffff);
		setRegistryName("storm_awakening");
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
	{
		if (entityLivingBaseIn instanceof PlayerEntity)
		{
			PlayerEntity attacker = (PlayerEntity) entityLivingBaseIn;
			if (attacker.isServerWorld())
			{
				double d = Helper.side(500);
				for (LivingEntity livingentity : attacker.world.getEntitiesWithinAABB(LivingEntity.class, attacker.getBoundingBox().grow(d, d, d)))
				{
					if (Helper.isEnemy(livingentity, attacker, null))
					{
						float damage = (float) (livingentity instanceof PlayerEntity ? 0.05 : 0.2) * livingentity.getMaxHealth();
						BlockPos blockpos = livingentity.getPosition();
						LightningBoltEntity lightningboltentity = new LightningBoltEntity(livingentity.world, (double) blockpos.getX() + 0.5D, (double) blockpos.getY(), (double) blockpos.getZ() + 0.5D, true);
						livingentity.attackEntityFrom(new IndirectEntityDamageSource("storm_awakening", lightningboltentity, attacker).setDamageIsAbsolute().setDamageBypassesArmor(), damage);
						lightningboltentity.setCaster(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
						((ServerWorld) livingentity.world).addLightningBolt(lightningboltentity);
						SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
						float f1 = 5.0F;
						livingentity.playSound(soundevent, f1, 1.0F);
					}
				}
			}
			if (Helper.getEscapeTime(attacker) > 0)
			{
				if (attacker.getAbsorptionAmount() < 0.3F * attacker.getMaxHealth())
				{
					attacker.setAbsorptionAmount(0.3F * attacker.getMaxHealth());
				}
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
}
