package com.thegrovesyproject.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import com.thegrovesyproject.main.MainRegistry;

import cpw.mods.fml.common.registry.EntityRegistry;

public class TMEntity {
	
	public static void mainRegistry(){
		registerEntity();
	}
	
	public static void registerEntity(){
		createEntity(EntityTutMob.class, "TutMob", 0xEC4545, 0x001EFF);
		createEntity(EntityTutHostileMob.class, "TutHostileMob", 0xE15050, 0xFFF700);
		
		createEntity(EntityGrenade.class, "TutGrenade", 0x008521, 0x00FF08);
	}
	
	public static void createEntity(Class<? extends Entity> class1, String entityName, int solidColour, int spotColour){
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerGlobalEntityID(class1, entityName, randomId);
		EntityRegistry.registerModEntity(class1, entityName, randomId, MainRegistry.modInstance, 64, 1, true);
		createEgg(randomId, solidColour, spotColour);
	}

	@SuppressWarnings("unchecked")
	private static void createEgg(int randomId, int solidColour, int spotColour) {
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColour, spotColour));
	}
}
