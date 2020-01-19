package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Doomsday extends KingOfWeapon
{
	private float attackDamage = Helper.attackDamage(60);
	private float attackSpeed = 0.3F;
	private float lifeSteal = 0.1F;
	private final HashMap<EquipmentSlotType,UUID> attackDamageModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType,UUID> attackSpeedModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType,UUID> lifeStealModifierMap = new HashMap<>();

	public Doomsday()
	{
		super(new KingOfMaterial(), Rarity.RARE);
		attackDamageModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackDamageModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		lifeStealModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		lifeStealModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("doomsday");
	}

	@Override
	public float getAttackDamage()
	{
		return attackDamage;
	}

	@Override
	public float getLifeSteal()
	{
		return lifeSteal;
	}

	@Override
	public float getAttackSpeed() { return attackSpeed; }

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(attackDamageModifierMap.get(equipmentSlot), "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(attackSpeedModifierMap.get(equipmentSlot), "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
			multimap.put(SharedKingAttributes.LIFE_STEAL.getName(), new AttributeModifier(lifeStealModifierMap.get(equipmentSlot), "Weapon modifier", getLifeSteal(), AttributeModifier.Operation.ADDITION));
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
}
