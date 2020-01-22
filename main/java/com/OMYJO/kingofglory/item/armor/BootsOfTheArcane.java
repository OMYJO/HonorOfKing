package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfArmorMaterial;
import com.OMYJO.kingofglory.other.KingOfItemTier;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BootsOfTheArcane extends KingOfArmor implements KingOfItem
{
	private float manaPer5Seconds = Helper.maxMana(25);
	private float magicPierce = 75;
	private float movementSpeed = Helper.movementSpeed(60);
	private final UUID MOVEMENT_SPEED_MODIFIER = BootsOfDivineSpeed.MOVEMENT_SPEED_MODIFIER;
	private final UUID MANA_PER_5_SECONDS_MODIFIER = UUID.randomUUID();
	private static final UUID MAGIC_MASTERY_MODIFIER = UUID.randomUUID();


	public BootsOfTheArcane()
	{
		super(new KingOfArmorMaterial().setName("boots_of_the_arcane"), EquipmentSlotType.FEET, Rarity.UNCOMMON);
		setRegistryName("boots_of_the_arcane");
	}

	@Override
	public float getMovementSpeed()
	{
		return movementSpeed;
	}

	@Override
	public float getManaPer5Seconds()
	{
		return manaPer5Seconds;
	}

	@Override
	public float getMagicPierce()
	{
		return magicPierce;
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
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".1"));
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
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(),new AttributeModifier(MOVEMENT_SPEED_MODIFIER,"Armor modifier", getMovementSpeed(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MANA_PER_5_SECONDS.getName(),new AttributeModifier(MANA_PER_5_SECONDS_MODIFIER,"Armor modifier", getManaPer5Seconds(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAGIC_PIERCE.getName(),new AttributeModifier(MAGIC_MASTERY_MODIFIER,"Armor modifier", getMagicPierce(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
