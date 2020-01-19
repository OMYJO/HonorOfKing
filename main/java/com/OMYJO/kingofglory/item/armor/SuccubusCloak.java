package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class SuccubusCloak extends KingOfArmor implements KingOfItem
{
	private float maxHealth = Helper.maxHealth(1000);
	private float magicDefence = 360;
	private final UUID maxHealthModifier = UUID.randomUUID();
	private final UUID magicDefenceModifier = UUID.randomUUID();

	public SuccubusCloak(EquipmentSlotType slot, String registryName)
	{
		super(new KingOfMaterial().setName("succubus_cloak"), slot, Rarity.RARE);
		setRegistryName(registryName);
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getMagicDefence()
	{
		return magicDefence;
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 *
	 * @param equipmentSlot
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == getEquipmentSlot())
		{
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(),new AttributeModifier(maxHealthModifier,"Armor modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAGIC_DEFENCE.getName(),new AttributeModifier(magicDefenceModifier,"Armor modifier", getMagicDefence(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}

	/**
	 * allows items to add custom lines of information to the mouseover description
	 *
	 * @param stack
	 * @param worldIn
	 * @param tooltip
	 * @param flagIn
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".0"));
	}


	/**
	 * Called to tick armor in the armor slot. Override to do something
	 *
	 * @param stack
	 * @param world
	 * @param player
	 */
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
	{
		super.onArmorTick(stack, world, player);
		if(player.getRevengeTarget() == null)
		{
			if(Helper.getEscapeTime(player) == 60)
			{
				if(player.getAbsorptionAmount() < Helper.maxHealth(200+15*120))
				{
					player.setAbsorptionAmount(Helper.maxHealth(200+15*120));
				}
			}
		}
	}
}
