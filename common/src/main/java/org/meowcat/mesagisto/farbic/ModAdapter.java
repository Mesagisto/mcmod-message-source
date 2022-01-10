package org.meowcat.mesagisto.farbic;

import net.fabricmc.api.ModInitializer;

public class ModAdapter implements ModInitializer {
	@Override
	public void onInitialize() {
		Mod.INSTANCE.onInitialize();
	}
}
