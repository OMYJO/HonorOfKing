package com.OMYJO.kingofglory.command;

import com.OMYJO.kingofglory.KingOfGlory;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
public class MyCommands
{
	@SubscribeEvent
	public static void register(FMLServerStartingEvent event)
	{
		CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
		LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
				Commands.literal("kingofglory")
						.then(CommandDataPanel.register(dispatcher))
		);
		//dispatcher.register(Commands.literal("tut").redirect(cmdTut));
	}
}
