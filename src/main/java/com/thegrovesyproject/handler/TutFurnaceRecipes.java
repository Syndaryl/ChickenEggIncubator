package com.thegrovesyproject.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.thegrovesyproject.block.TMBlock;
import com.thegrovesyproject.item.TMItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TutFurnaceRecipes {

	private static final TutFurnaceRecipes SMELTING_BASE = new TutFurnaceRecipes();

	private Map<ItemStack, ItemStack> smeltingList = new HashMap<ItemStack, ItemStack>();
	private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

	public static TutFurnaceRecipes smelting() {
		return SMELTING_BASE;
	}
	
	private TutFurnaceRecipes(){
		this.addRecipie(TMItem.tutCrop, new ItemStack(TMItem.tutGrenade), 0.8F);
		this.addRecipie(Item.getItemFromBlock(TMBlock.tutChest), new ItemStack(TMItem.tutHelmet), 0.8F);
	}
	
	public void addRecipie(Item item, ItemStack itemstack, float experience){
		this.addLists(item, itemstack, experience);
	}
	
	public void addLists(Item item, ItemStack itemstack, float experience){
		this.putLists(new ItemStack(item, 1, 32767), itemstack, experience);
	}
	
	public void putLists(ItemStack itemstack, ItemStack itemstack2, float experience){
		this.smeltingList.put(itemstack, itemstack2);
		this.experienceList.put(itemstack2, Float.valueOf(experience));
	}

	public ItemStack getSmeltingResult(ItemStack itemstack) {
		Iterator<Entry<ItemStack, ItemStack>> iterator = this.smeltingList.entrySet().iterator();
		Entry<ItemStack, ItemStack> entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}
			entry = iterator.next();
		} while (!canBeSmelted(itemstack, (ItemStack) entry.getKey()));
		return (ItemStack) entry.getValue();
	}

	private boolean canBeSmelted(ItemStack itemstack, ItemStack itemstack2) {
		return itemstack2.getItem() == itemstack.getItem() && (itemstack2.getItemDamage() == 32767 || itemstack2.getItemDamage() == itemstack.getItemDamage());
	}
	
	public float giveExperience(ItemStack itemstack){
		Iterator<Entry<ItemStack, Float>> iterator = this.experienceList.entrySet().iterator();
		Entry<ItemStack, Float> entry;
		
		do{
			if(!iterator.hasNext()){
				return 0.0f;
			}
			
			entry = iterator.next();
		}
		while(!this.canBeSmelted(itemstack, (ItemStack) entry.getKey()));
		
		if(itemstack.getItem().getSmeltingExperience(itemstack) != -1){
				return itemstack.getItem().getSmeltingExperience(itemstack);
		}
		
		return ((Float) entry.getValue()).floatValue();
	}
}
