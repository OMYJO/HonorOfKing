package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.item.TwilightBow;
import com.OMYJO.kingofglory.other.Convertor;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Damage
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
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
					//精准、破败
					{
						int n = 1;
						if (source.getImmediateSource() instanceof AbstractArrowEntity)
						{
							n = 2;
						}
						for (int i = 0; i < n; i++)
						{
							if (attacker.getHeldItemMainhand().getItem() instanceof TwilightBow || attacker.getHeldItemOffhand().getItem() instanceof TwilightBow)
							{
								event.setAmount(event.getAmount() + Convertor.attackDamage(35) * base / (float) attacker.getAttributes().getAttributeInstanceByName(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).getValue());
							}
							//else if 纯净苍穹
							//else if 速击之枪
							//if破晓
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
				//破军
				{

				}
			}
		}

		//抗性计算
		if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion() || source.isUnblockable())
		{

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
	public void onCriticalHit(CriticalHitEvent event)
	{
		if(event.getPlayer().getHeldItemMainhand().getItem() instanceof KingOfItem)
		{
			event.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public void onLivingDamage(LivingDamageEvent event)
	{
		Entity entity = event.getSource().getTrueSource();
		if(entity instanceof LivingEntity)
		{
			LivingEntity attacker = (LivingEntity)entity;
			DamageSource source = event.getSource();
			if (source.isDamageAbsolute() || source.isFireDamage() || source.isExplosion() || source.isUnblockable())
			{

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
