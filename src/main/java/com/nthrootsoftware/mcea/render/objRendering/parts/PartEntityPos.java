package com.nthrootsoftware.mcea.render.objRendering.parts;

import com.nthrootsoftware.mcea.render.objRendering.ModelObj;

import net.minecraft.entity.Entity;

/**
 * Part for tracking the position of the model.
 */
public class PartEntityPos extends Part
{

	public PartEntityPos(ModelObj mObj) 
	{
		super(mObj, "entitypos");
	}

	public void move(Entity entity) 
	{
		entity.posX = valueX;
		entity.posY = valueY;
		entity.posZ = valueZ;
	}

}