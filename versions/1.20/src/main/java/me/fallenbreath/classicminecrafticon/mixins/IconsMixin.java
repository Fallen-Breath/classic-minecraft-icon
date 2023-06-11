/*
 * This file is part of the Classic Minecraft Icon project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
 *
 * Classic Minecraft Icon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Classic Minecraft Icon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Classic Minecraft Icon.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.fallenbreath.classicminecrafticon.mixins;

import me.fallenbreath.classicminecrafticon.ClassMinecraftIconStorage;
import net.minecraft.client.util.Icons;
import net.minecraft.resource.InputSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.util.List;

/**
 * The implementation for mc [1.20, ~)
 * See {@link MinecraftClientMixin} in subproject 1.15.2 for implementation for mc (~, 1.20)
 */
@Mixin(Icons.class)
public abstract class IconsMixin
{
	@Inject(method = "getIcons", at = @At("HEAD"), cancellable = true)
	private void bringTheClassicCraftingTableIconBack_general(CallbackInfoReturnable<List<InputSupplier<InputStream>>> cir)
	{
		cir.setReturnValue(ClassMinecraftIconStorage.getAllPngResources());
	}

	@Inject(method = "getMacIcon", at = @At("HEAD"), cancellable = true)
	private void bringTheClassicCraftingTableIconBack_mac( CallbackInfoReturnable<InputSupplier<InputStream>> cir)
	{
		cir.setReturnValue(ClassMinecraftIconStorage.getResource("minecraft.icns"));
	}
}
