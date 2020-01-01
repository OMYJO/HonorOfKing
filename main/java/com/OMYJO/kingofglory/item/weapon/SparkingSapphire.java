package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SparkingSapphire extends KingOfWeapon
{
	private float mana = Helper.maxMana(300);
	private final HashMap<EquipmentSlotType, UUID> manaModifierMap = new HashMap<>();

	public SparkingSapphire()
	{
		super(new KingOfMaterial().addMaxUses(Helper.maxMana(300)), Rarity.COMMON);
		manaModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		manaModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("sparking_sapphire");
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public float getMana()
	{
		return mana;
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 *
	 * @param equipmentSlot
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.MAX_MANA.getName(), new AttributeModifier(manaModifierMap.get(equipmentSlot), "Weapon modifier", getMana(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
