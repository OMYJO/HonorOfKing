package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.item.armor.*;
import com.OMYJO.kingofglory.item.bow.DayBreaker;
import com.OMYJO.kingofglory.item.bow.TwilightBow;
import com.OMYJO.kingofglory.item.weapon.*;
import com.OMYJO.kingofglory.other.Helper;
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
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
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
					if(attacker.getHeldItemMainhand().getItem() instanceof Nightmare || attacker.getHeldItemOffhand().getItem() instanceof Nightmare)
					{
						if(Math.random()<0.3F)
						{
							target.addPotionEffect(new EffectInstance(Effects.CRIPPLE,40));
						}
					}
					else if(attacker.getHeldItemMainhand().getItem() instanceof SunglowStriker || attacker.getHeldItemOffhand().getItem() instanceof SunglowStriker)
					{
						if(Math.random()<0.3F)
						{
							target.addPotionEffect(new EffectInstance(Effects.CRIPPLE,40));
						}
					}

					//碎冰
					if(attacker.getHeldItemMainhand().getItem() instanceof FrigidLance || attacker.getHeldItemOffhand().getItem() instanceof FrigidLance)
					{
						int time = 20;
						if(!(source.getImmediateSource() instanceof AbstractArrowEntity))
						{
							time *= 2;
						}
						target.addPotionEffect(new EffectInstance(Effects.CRUSHING_ICE,time));
					}


					//普攻制裁
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
									int cooldown = (int)(10 * (1-attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.COOL_DOWN_REDUCTION.getName()).getValue()));
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
											float damage = Helper.attackDamage(100) + 0.3F * (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue();
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
									float damage = Helper.attackDamage(100) + 0.3F * (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue();
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

					//精准
					{
						int n = 1;
						if (source.getImmediateSource() instanceof AbstractArrowEntity)
						{
							n = 2;
						}
						for (int i = 0; i < n; i++)//远程英雄使用翻倍
						{
							//if 逐日之弓
							if (attacker.getHeldItemMainhand().getItem() instanceof TwilightBow || attacker.getHeldItemOffhand().getItem() instanceof TwilightBow)
							{
								event.setAmount(event.getAmount() + Helper.attackDamage(35) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}
							//else if 纯净苍穹
							if (attacker.getHeldItemMainhand().getItem() instanceof PureSky || attacker.getHeldItemOffhand().getItem() instanceof PureSky)
							{
								event.setAmount(event.getAmount() + Helper.attackDamage(35) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}
							//else if 速击之枪
							else if (attacker.getHeldItemMainhand().getItem() instanceof SwiftStrikeLance || attacker.getHeldItemOffhand().getItem() instanceof SwiftStrikeLance)
							{
								event.setAmount(event.getAmount() + Helper.attackDamage(30) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}

							//破晓
							if (attacker.getHeldItemMainhand().getItem() instanceof DayBreaker || attacker.getHeldItemOffhand().getItem() instanceof DayBreaker)
							{
								event.setAmount(event.getAmount() + Helper.attackDamage(50) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}
						}
					}

					//暴击
					{
						double r = Math.random();
						if (r < attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_CHANCE.getName()).getValue())
						{
							event.setAmount(event.getAmount() * (float) attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_DAMAGE.getName()).getValue());
							//影刃
							if (attacker.getHeldItemMainhand().getItem() instanceof ShadowRipper || attacker.getHeldItemOffhand().getItem() instanceof ShadowRipper)
							{
								attacker.addPotionEffect(new EffectInstance(Effects.STORM));
							}
						}
					}

					//末世
					if (attacker.getHeldItemMainhand().getItem() instanceof Doomsday || attacker.getHeldItemOffhand().getItem() instanceof Doomsday)
					{
						float amount =0.08F * target.getHealth() * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue();
						if(!(target instanceof PlayerEntity))
						{
							amount = Math.min(amount, Helper.attackDamage(80));
						}
						event.setAmount(event.getAmount() + amount);
					}

					//强击：蓝刀4》巫法3》宗师2》冰脉1》光辉0
					if(attacker.isPotionActive(Effects.ASSAULTING))
					{
						switch (attacker.getActivePotionEffect(Effects.ASSAULTING).getAmplifier())
						{
							case 2:
								event.setAmount(event.getAmount() + 0.8F * (float)attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
								attacker.removePotionEffect(Effects.ASSAULTING);
								break;
							case 1:
								int amplifier = source instanceof IndirectEntityDamageSource?0:1;
								event.setAmount(event.getAmount() + Helper.attackDamage(450));
								target.addPotionEffect(new EffectInstance(Effects.ASSAULTING_SLOWNESS,20,amplifier));
								for (LivingEntity livingentity : target.world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(2.0D, 0.5D, 2D)))
								{
									if (livingentity != attacker && livingentity != target && !attacker.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && !(livingentity instanceof AnimalEntity))
									{
										livingentity.addPotionEffect(new EffectInstance(Effects.ASSAULTING_SLOWNESS,20,amplifier));
									}
								}
								break;
							case 0:
								event.setAmount(event.getAmount() + 0.5F * (float)attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue()+ 0.3F * (float)attacker.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_ATTACK.getName()).getValue());
								attacker.removePotionEffect(Effects.ASSAULTING);
								event.getSource().setMagicDamage();
								break;
						}
					}
					if(target.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof BootsOfForitude)
					{
						event.setAmount(event.getAmount()*(1-0.15F));
					}
				}
				else
				{
					//非普攻制裁
					if(attacker.getHeldItemMainhand().getItem() instanceof DivinePunisher || attacker.getHeldItemOffhand().getItem() instanceof DivinePunisher)
					{
						target.addPotionEffect(new EffectInstance(Effects.SEVERE_WOUND,30));
					}
				}
				//破军
				if(attacker.getHeldItemMainhand().getItem() instanceof SiegeBreaker || attacker.getHeldItemOffhand().getItem() instanceof SiegeBreaker)
				{
					if(target.getHealth()*2<target.getMaxHealth())
					{
						event.setAmount(event.getAmount()*(1+0.3F));
					}
				}
				//无畏
				if(attacker.isPotionActive(Effects.FEARLESS))
				{
					event.setAmount(event.getAmount() * (1 + (0.02F * attacker.getActivePotionEffect(Effects.FEARLESS).getAmplifier() + 1)));
				}


				//触发强击
				if(attacker instanceof PlayerEntity)
				{
					//宗师
					if(attacker.getHeldItemMainhand().getItem() instanceof MasterSword || attacker.getHeldItemOffhand().getItem() instanceof MasterSword)
					{
						ItemStack itemStack = attacker.getHeldItemMainhand().getItem() instanceof MasterSword? attacker.getHeldItemMainhand():attacker.getHeldItemOffhand();
						if(!((PlayerEntity) attacker).getCooldownTracker().hasCooldown(itemStack.getItem()))
						{
							((PlayerEntity) attacker).getCooldownTracker().setCooldown(itemStack.getItem(),40);
							itemStack.damageItem(1, attacker, (p_220045_0_) -> {
								p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
							});
							attacker.addPotionEffect(new EffectInstance(Effects.ASSAULTING,100,2));
							attacker.addPotionEffect(new EffectInstance(Effects.ASSAULTING_SPEED,40));
						}
					}
					//else if 冰脉
					else if(attacker.getHeldItemMainhand().getItem() instanceof FrostscarsEmbrace || attacker.getHeldItemOffhand().getItem() instanceof FrostscarsEmbrace)
					{
						ItemStack itemStack = attacker.getHeldItemMainhand().getItem() instanceof SwordOfGlory? attacker.getHeldItemMainhand():attacker.getHeldItemOffhand();
						if(!((PlayerEntity) attacker).getCooldownTracker().hasCooldown(itemStack.getItem()))
						{
							((PlayerEntity) attacker).getCooldownTracker().setCooldown(itemStack.getItem(),60);
							itemStack.damageItem(1, attacker, (p_220045_0_) -> {
								p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
							});
							attacker.addPotionEffect(new EffectInstance(Effects.ASSAULTING,100,1));
						}
					}
					//else if 光辉之剑
					else if(attacker.getHeldItemMainhand().getItem() instanceof SwordOfGlory || attacker.getHeldItemOffhand().getItem() instanceof SwordOfGlory)
					{
						ItemStack itemStack = attacker.getHeldItemMainhand().getItem() instanceof SwordOfGlory? attacker.getHeldItemMainhand():attacker.getHeldItemOffhand();
						if(!((PlayerEntity) attacker).getCooldownTracker().hasCooldown(itemStack.getItem()))
						{
							((PlayerEntity) attacker).getCooldownTracker().setCooldown(itemStack.getItem(),40);
							itemStack.damageItem(1, attacker, (p_220045_0_) -> {
								p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
							});
							attacker.addPotionEffect(new EffectInstance(Effects.ASSAULTING,100,0));
						}
					}
				}
			}
			//if触发不祥
			if(target.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof OminousPremonition)
			{
				attacker.addPotionEffect(new EffectInstance(Effects.COLD_IRON,60,1));
			}
			//else if触发守护者
			else if(target.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof ProtectorsVest)
			{
				attacker.addPotionEffect(new EffectInstance(Effects.COLD_IRON,60,0));
			}
		}

		//抗性计算
		if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion())
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

		//苍穹驱散
		if(event.getEntityLiving().isPotionActive(Effects.DISPELLING))
		{
			event.setAmount(event.getAmount()/2);
		}
		//名刀
		if(event.getEntityLiving().isPotionActive(Effects.DARK_CURTAIN))
		{
			event.setCanceled(true);
			event.setAmount(0);
			return;
		}
		//暴烈之甲触发无畏
		if(target.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof CuirassOfSavagery)
		{
			if(target.getActivePotionEffect(Effects.FEARLESS) == null)
			{
				target.addPotionEffect(new EffectInstance(Effects.FEARLESS,60));
			}
			else
			{
				int amplifier = target.getActivePotionEffect(Effects.FEARLESS).getAmplifier();
				target.addPotionEffect(new EffectInstance(Effects.FEARLESS,60,Math.min(4,amplifier+1)));
			}
		}

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
		DamageSource source = event.getSource();
		Entity entity = event.getSource().getTrueSource();
		LivingEntity target = event.getEntityLiving();
		//名刀触发
		if(event.getAmount() >= event.getEntityLiving().getHealth())
		{
			if(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof Destiny || event.getEntityLiving().getHeldItemOffhand().getItem() instanceof Destiny)
			{
				ItemStack itemStack = event.getEntityLiving().getHeldItemMainhand().getItem() instanceof Destiny ? event.getEntityLiving().getHeldItemMainhand():event.getEntityLiving().getHeldItemOffhand();
				boolean flag = false;
				if(itemStack.getDamage() <= 0)
				{
					itemStack.setDamage(itemStack.getMaxDamage() - 1);
					flag = true;
					if(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ShootableItem)
					{
						event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.DARK_CURTAIN,10));
					}
					else
					{
						event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.DARK_CURTAIN,20));
					}
				}
				if(flag)
				{
					event.setCanceled(true);
					event.setAmount(0);
					return;
				}
			}
		}

		//触发冰心
		if(event.getAmount()*10>target.getMaxHealth())
		{
			if(target.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() instanceof GlacialBuckler)
			{
				ItemStack itemStack = target.getItemStackFromSlot(EquipmentSlotType.LEGS);
				boolean flag = false;
				if(target instanceof PlayerEntity)
				{
					flag = ((PlayerEntity) target).getCooldownTracker().hasCooldown(itemStack.getItem());
					if(!flag)
					{
						int cooldown = (int)(40 * (1-target.getAttributes().getAttributeInstanceByName(SharedKingAttributes.COOL_DOWN_REDUCTION.getName()).getValue()));
						((PlayerEntity) target).getCooldownTracker().setCooldown(itemStack.getItem(),cooldown);
					}
				}
				if (!flag)
				{
					for (LivingEntity livingentity : target.world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(2.0D, 0.5D, 2D)))
					{
						if (livingentity != target && !target.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && !(livingentity instanceof AnimalEntity))
						{
							livingentity.attackEntityFrom(new IndirectEntityDamageSource("ice_heart",target,target).setMagicDamage().setDamageBypassesArmor(), Helper.attackDamage(200));
							livingentity.addPotionEffect(new EffectInstance(Effects.ICE_HEART,40));
						}
					}
				}
			}
		}



		//反甲
		if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion() || source.isMagicDamage())
		{
			//nothing to do
		}
		else
		{
			if(entity instanceof LivingEntity)
			{
				LivingEntity attacker = (LivingEntity) entity;
				if(target.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() instanceof Spikemail)
				{
					float up = 0.2F;
					up += 0.01F * target.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR.getName()).getValue()/20;
					up = Math.min(up,0.4F);
					float down = 1F;
					down -= 0.3F * target.getDistance(attacker)/16;
					down = Math.max(down,0.7F);
					attacker.attackEntityFrom(new IndirectEntityDamageSource("thorns",target,target).setMagicDamage().setDamageBypassesArmor(),event.getAmount()*up*down);
				}
			}
		}


		//吸血
		if(entity instanceof LivingEntity)
		{
			LivingEntity attacker = (LivingEntity)entity;
			if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion())
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
	public static void onLivingDeath(LivingDeathEvent event)
	{
		LivingEntity livingEntity = event.getEntityLiving();
		if (event.getEntityLiving().getHeldItemMainhand().getItem() instanceof SagesSanctuary || event.getEntityLiving().getHeldItemOffhand().getItem() instanceof SagesSanctuary)
		{
			ItemStack itemStack = event.getEntityLiving().getHeldItemMainhand().getItem() instanceof SagesSanctuary ? event.getEntityLiving().getHeldItemMainhand() : event.getEntityLiving().getHeldItemOffhand();
			event.setCanceled(true);
			livingEntity.setHealth(Helper.maxHealth(2000 + 1500));
			itemStack.shrink(1);
		}
	}
}
