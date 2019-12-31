package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.KingOfGlory;
import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.item.bow.DayBreaker;
import com.OMYJO.kingofglory.item.bow.TwilightBow;
import com.OMYJO.kingofglory.item.weapon.DivinePunisher;
import com.OMYJO.kingofglory.item.weapon.SparkForgedDagger;
import com.OMYJO.kingofglory.item.weapon.SunglowStriker;
import com.OMYJO.kingofglory.item.weapon.SwiftStrikeLance;
import com.OMYJO.kingofglory.other.Convertor;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.OMYJO.kingofglory.potion.Effects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Damage
{
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event)
	{
		DamageSource source = event.getSource();
		Entity entity = source.getTrueSource();
		LivingEntity target = event.getEntityLiving();
		if (entity instanceof LivingEntity)
		{
			LivingEntity attacker = (LivingEntity) entity;
			ItemStack stack = attacker.getHeldItemMainhand();
			if (stack.getItem() instanceof KingOfItem)
			{
				float base = event.getAmount();
				if ((!(source instanceof IndirectEntityDamageSource) || source.getImmediateSource() instanceof AbstractArrowEntity))//普通攻击
				{
					//残废
					if(attacker.getHeldItemMainhand().getItem() instanceof SunglowStriker || attacker.getHeldItemOffhand().getItem() instanceof SunglowStriker)
					{
						if(Math.random()<0.3F)
						{
							target.addPotionEffect(new EffectInstance(Effects.CRIPPLE,40));
						}
					}

					//重伤
					if(attacker.getHeldItemMainhand().getItem() instanceof DivinePunisher || attacker.getHeldItemOffhand().getItem() instanceof DivinePunisher)
					{
						target.addPotionEffect(new EffectInstance(Effects.SEVERE_WOUND,60));
					}

					//电弧
					if(attacker.getHeldItemMainhand().getItem() instanceof SparkForgedDagger || attacker.getHeldItemOffhand().getItem() instanceof SparkForgedDagger)
					{
						SparkForgedDagger sparkForgedDagger = (SparkForgedDagger) (attacker.getHeldItemMainhand().getItem() instanceof SparkForgedDagger?attacker.getHeldItemMainhand().getItem() : attacker.getHeldItemOffhand().getItem());
						if(Math.random()<0.3F)
						{
							boolean flag = false;
							if(attacker instanceof PlayerEntity)
							{
								flag = ((PlayerEntity) attacker).getCooldownTracker().hasCooldown(sparkForgedDagger);
								if(!flag)
								{
									int cooldown = (int)(10 * (1-attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.COOLDOWN_REDUCTION.getName()).getValue()));
									((PlayerEntity) attacker).getCooldownTracker().setCooldown(sparkForgedDagger,cooldown);
								}
							}
							if (!flag)
							{
								if (attacker.isServerWorld())
								{
									for (LivingEntity livingentity : target.world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(5.0D, 1.25D, 5.0D)))
									{
										if (livingentity != attacker && livingentity != target && !attacker.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && !(livingentity instanceof AnimalEntity))
										{
											float damage = Convertor.attackDamage(100) + 0.3F * (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue();
											if (Math.random() < attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_CHANCE.getName()).getValue())
											{
												damage *= 2;
											}
											BlockPos blockpos = livingentity.getPosition();
											LightningBoltEntity lightningboltentity = new LightningBoltEntity(livingentity.world, (double) blockpos.getX() + 0.5D, (double) blockpos.getY(), (double) blockpos.getZ() + 0.5D, true);
											livingentity.attackEntityFrom(new IndirectEntityDamageSource("electric_arc", lightningboltentity, attacker).setMagicDamage().setDamageBypassesArmor(), damage);
											lightningboltentity.setCaster(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
											((ServerWorld) livingentity.world).addLightningBolt(lightningboltentity);
											SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
											float f1 = 5.0F;
											livingentity.playSound(soundevent, f1, 1.0F);
										}
									}
									float damage = Convertor.attackDamage(100) + 0.3F * (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue();
									if (Math.random() < attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_CHANCE.getName()).getValue())
									{
										damage *= 2;
									}
									BlockPos blockpos = target.getPosition();
									LightningBoltEntity lightningboltentity = new LightningBoltEntity(target.world, (double) blockpos.getX() + 0.5D, (double) blockpos.getY(), (double) blockpos.getZ() + 0.5D, true);
									event.setAmount(event.getAmount() + damage);
									lightningboltentity.setCaster(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
									((ServerWorld) target.world).addLightningBolt(lightningboltentity);
									SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
									float f1 = 5.0F;
									target.playSound(soundevent, f1, 1.0F);
								}
							}
						}
					}


					//精准、破败
					{
						int n = 1;
						if (source.getImmediateSource() instanceof AbstractArrowEntity)
						{
							n = 2;
						}
						for (int i = 0; i < n; i++)//远程英雄使用翻倍
						{
							if (attacker.getHeldItemMainhand().getItem() instanceof TwilightBow || attacker.getHeldItemOffhand().getItem() instanceof TwilightBow)
							{
								event.setAmount(event.getAmount() + Convertor.attackDamage(35) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}
							//else if 纯净苍穹
							else if (attacker.getHeldItemMainhand().getItem() instanceof SwiftStrikeLance || attacker.getHeldItemOffhand().getItem() instanceof SwiftStrikeLance)
							{
								event.setAmount(event.getAmount() + Convertor.attackDamage(30) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}

							//破晓
							if (attacker.getHeldItemMainhand().getItem() instanceof DayBreaker || attacker.getHeldItemOffhand().getItem() instanceof DayBreaker)
							{
								event.setAmount(event.getAmount() + Convertor.attackDamage(50) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}
						}
						//if末世
					}
					//暴击
					{
						double r = Math.random();
						if (r < attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_CHANCE.getName()).getValue())
						{
							event.setAmount(event.getAmount() * (float) attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_DAMAGE.getName()).getValue());
							//影刃
						}
					}
					//强击：蓝刀4》巫法3》宗师2》冰脉1》光辉0
					{

					}
				}
				else
				{
					//重伤
					if(attacker.getHeldItemMainhand().getItem() instanceof DivinePunisher || attacker.getHeldItemOffhand().getItem() instanceof DivinePunisher)
					{
						target.addPotionEffect(new EffectInstance(Effects.SEVERE_WOUND,30));
					}
				}
				//破军
				{

				}
			}
		}

		//抗性计算
		if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion() || source.isUnblockable())
		{
			//nothing to do
		}
		else
		{
			if(source.isMagicDamage())
			{
				double magicDefence = target.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_DEFENCE.getName()).getValue();
				double magicPierce = 0D;
				double magicPierceRate = 0D;
				if(entity instanceof LivingEntity)
				{
					LivingEntity attacker = (LivingEntity) entity;
					magicPierce = (double)((int)attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_PIERCE.getName()).getValue());
					magicPierceRate = attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_PIERCE.getName()).getValue() - magicPierce;
				}
				magicDefence = Math.max(magicDefence - magicPierce,0D);
				magicDefence = magicDefence * (1-magicPierceRate);
				event.setAmount(event.getAmount() * 602 / (float) (magicDefence + 602));
			}
			else
			{
				double armor = target.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR.getName()).getValue();
				double armorPierce = 0D;
				double armorPierceRate = 0D;
				if(entity instanceof LivingEntity)
				{
					LivingEntity attacker = (LivingEntity) entity;
					armorPierce = (double)((int)attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR_PIERCE.getName()).getValue());
					armorPierceRate = attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR_PIERCE.getName()).getValue() - armorPierce;
				}
				armor = Math.max(armor - armorPierce,0D);
				armor = armor * (1-armorPierceRate);
				event.setAmount(event.getAmount() * 602 / (float) (armor + 602));
			}
		}
		//苍穹
		//名刀被动
		//反甲、不详、冰心、守护者之铠
	}

	@SubscribeEvent
	public static void onCriticalHit(CriticalHitEvent event)
	{
		if(event.getPlayer().getHeldItemMainhand().getItem() instanceof KingOfItem)
		{
			event.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event)
	{
		Entity entity = event.getSource().getTrueSource();
		if(entity instanceof LivingEntity)
		{
			LivingEntity attacker = (LivingEntity)entity;
			DamageSource source = event.getSource();
			if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion() || source.isUnblockable())
			{
				//nothing to do
			}
			else
			{
				if (source.isMagicDamage())
				{
					double magicLifeSteal = attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_LIFE_STEAL.getName()).getValue();
					float damage = event.getAmount();
					event.getEntityLiving().heal(damage * (float) magicLifeSteal);
				}
				else
				{
					double lifeSteal = attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.LIFE_STEAL.getName()).getValue();
					float damage = event.getAmount();
					event.getEntityLiving().heal(damage * (float) lifeSteal);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		LivingEntity livingEntity = event.getEntityLiving();
		//触发名刀
		//触发复活甲
	}
}
