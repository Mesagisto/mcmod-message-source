package org.meowcat.mesagisto.fabric;

import net.fabricmc.api.ModInitializer;

public class ModAdapter implements ModInitializer {
	@Override
	public void onInitialize() {
		Mod.INSTANCE.onInitialize();
	}
}
