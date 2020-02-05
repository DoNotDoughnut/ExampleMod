package com.github.donotdoughnut.examplemod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

	World getClientWorld();

	PlayerEntity getClientPlayer();
	
}
