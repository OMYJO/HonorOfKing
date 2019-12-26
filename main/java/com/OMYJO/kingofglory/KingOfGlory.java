package com.OMYJO.kingofglory;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("kingofglory")
public class KingOfGlory
{
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	public KingOfGlory() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
