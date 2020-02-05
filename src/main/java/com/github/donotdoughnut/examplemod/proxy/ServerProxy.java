package com.github.donotdoughnut.examplemod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

	@Override
	public World getClientWorld() {
		throw new IllegalStateException("Can only run this on the client-side");
	}

	@Override
	public PlayerEntity getClientPlayer() {
		throw new IllegalStateException("Can only run this on the client-side");
	}


}
