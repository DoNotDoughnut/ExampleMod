package com.github.donotdoughnut.examplemod.api.accessory.basic;

import com.github.donotdoughnut.examplemod.api.accessory.ExampleModAccessory;

public class BasicModAccessory extends ExampleModAccessory {

	public BasicModAccessory(String regName) {
		super(regName);
	}

	public boolean canRightClickEquip() {
		return true;
	}

}
