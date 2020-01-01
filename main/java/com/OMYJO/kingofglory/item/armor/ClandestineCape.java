package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ClandestineCape extends KingOfArmor implements KingOfItem
{
	private float magicDefence = 120;
	private float maxHealth = Helper.maxHealth(700);
	private float HPPer5Seconds = Helper.maxHealth(50);
	private final UUID maxHealthModifier = UUID.randomUUID();
	private final UUID magicDefenceModifier = UUID.randomUUID();
	private final UUID HPPer5SecondsModifier = UUID.randomUUID();

	public ClandestineCape(EquipmentSlotType slot, String registryName)
	{
		super(new KingOfMaterial().setName("clandestine_cape"), slot, Rarity.UNCOMMON);
		setRegistryName(registryName);
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getMagicDefence() { return magicDefence; }

	@Override
	public float getHPPer5Seconds() { return HPPer5Seconds; }

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
			multimap.put(SharedKingAttributes.MAX_HEALTH.getName(),new AttributeModifier(maxHealthModifier,"Armor modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAGIC_DEFENCE.getName(),new AttributeModifier(magicDefenceModifier,"Armor modifier", getMagicDefence(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.HP_PER_5_SECONDS.getName(),new AttributeModifier(HPPer5SecondsModifier,"Armor modifier",getHPPer5Seconds(), AttributeModifier.Operation.ADDITION));
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
	}
}
