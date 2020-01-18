package com.OMYJO.kingofglory.item.bow;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.potion.Effects;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.item.Items;

import java.util.UUID;

public abstract class KingOfBow extends BowItem implements KingOfItem
{
	private final IItemTier tier;
	public static final UUID MOVEMENT_SPEED_MODIFIER = UUID.randomUUID();
	public KingOfBow(IItemTier tier,Rarity rarity)
	{
		super(new Item.Properties().maxDamage(tier.getMaxUses()).group(ItemGroup.COMBAT).rarity(rarity));
		this.addPropertyOverride(new ResourceLocation("pull"), (itemStack, world, livingEntity) -> {
			if (livingEntity == null) {
				return 0.0F;
			} else {
				return !(livingEntity.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 2.0F;
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), (itemStack, world, livingEntity) -> {
			return livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F;
		});
		this.tier = tier;
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 *
	 * @param stack
	 * @param worldIn
	 * @param entityLiving
	 * @param timeLeft
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
	{
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.ARROW);
				}

				float f = getArrowVelocity(i);
				if (!((double)f < 0.1D)) {
					boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
					if (!worldIn.isRemote) {
						ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
						AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
						abstractarrowentity = customeArrow(abstractarrowentity);
						abstractarrowentity.setDamage(playerentity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() / 5);
						if(playerentity.isPotionActive(Effects.CHASING_SUN))
						{
							abstractarrowentity.setDamage(abstractarrowentity.getDamage()/1.2F);
						}
						abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F * (playerentity.isPotionActive(Effects.CHASING_SUN)?1.2F:1F), 1.0F);



						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						if (k > 0) {
							abstractarrowentity.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							abstractarrowentity.setFire(100);
						}

						stack.damageItem(1, playerentity, (p_220009_1_) -> {
							p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
						});
						if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
							abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						}

						worldIn.addEntity(abstractarrowentity);
					}

					worldIn.playSound((PlayerEntity)null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (!flag1 && !playerentity.abilities.isCreativeMode) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							playerentity.inventory.deleteStack(itemstack);
						}
					}

					stack.damageItem(1, playerentity, (p_220009_1_) -> {
						p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
					});

					playerentity.getCooldownTracker().setCooldown(this, (int)Math.min(
								20 * playerentity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue()
									/ playerentity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED).getValue()
						,100D));
					playerentity.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}


	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	@Override
	public int getItemEnchantability()
	{
		return super.getItemEnchantability();
	}

	/**
	 * Checks whether an item can be enchanted with a certain enchantment. This
	 * applies specifically to enchanting an item in the enchanting table and is
	 * called when retrieving the list of possible enchantments for an item.
	 * Enchantments may additionally (or exclusively) be doing their own checks in
	 * {@link Enchantment#canApplyAtEnchantingTable(ItemStack)};
	 * check the individual implementation for reference. By default this will check
	 * if the enchantment type is valid for this item type.
	 *
	 * @param stack       the item stack to be enchanted
	 * @param enchantment the enchantment to be applied
	 * @return true if the enchantment can be applied to this item
	 */
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
	{
		if(enchantment == Enchantments.PUNCH)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else if(enchantment == Enchantments.FLAME)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else if(enchantment == Enchantments.INFINITY)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else if(enchantment == Enchantments.UNBREAKING)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else if(enchantment == Enchantments.MENDING)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else
		{
			return false;
		}
	}

	/**
	 * Gets the velocity of the arrow entity from the bow's charge
	 */
	public static float getArrowVelocity(int charge)
	{
		float f = (float)charge / 2.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	 * update it's contents.
	 *
	 * @param stack
	 * @param worldIn
	 * @param entityIn
	 * @param itemSlot
	 * @param isSelected
	 */
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		long time = worldIn.getDayTime();
		if(time % 1000 == 0)
		{
			stack.setDamage(stack.getDamage() - (int)(((PlayerEntity)entityIn).getAttributes().getAttributeInstance(SharedKingAttributes.MANA_PER_5_SECONDS).getValue()));
		}
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if(equipmentSlot == EquipmentSlotType.MAINHAND)
		{
			multimap.put(SharedKingAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Weapon modifier", (Helper.movementSpeed(-50)), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
