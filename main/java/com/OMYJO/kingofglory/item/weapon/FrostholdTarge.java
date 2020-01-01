package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Convertor;
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

public class FrostholdTarge extends KingOfWeapon
{
	private float coolDownReduction = 0.1F;
	private float armor = 110;
	private float mana = Convertor.maxMana(400);
	private final HashMap<EquipmentSlotType, UUID> coolDownReductionModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> armorModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> manaModifierMap = new HashMap<>();

	public FrostholdTarge()
	{
		super(new KingOfMaterial().addMaxUses(Convertor.maxMana(400)), Rarity.UNCOMMON);
		coolDownReductionModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		coolDownReductionModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		armorModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		armorModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		manaModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		manaModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("frosthold_targe");
	}

	@Override
	public float getCoolDownReduction()
	{
		return coolDownReduction;
	}

	@Override
	public float getArmor()
	{
		return armor;
	}

	@Override
	public float getMana()
	{
		return mana;
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
			multimap.put(SharedKingAttributes.COOL_DOWN_REDUCTION.getName(), new AttributeModifier(coolDownReductionModifierMap.get(equipmentSlot), "Weapon modifier", getCoolDownReduction(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.ARMOR.getName(),new AttributeModifier(armorModifierMap.get(equipmentSlot),"Weapon modifier",getArmor(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAX_MANA.getName(), new AttributeModifier(manaModifierMap.get(equipmentSlot), "Weapon modifier", getMana(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
