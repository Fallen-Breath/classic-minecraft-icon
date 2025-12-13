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

import me.fallenbreath.classicminecrafticon.ClassicMinecraftIconStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.IoSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.io.InputStream;

/**
 * The implementation for mc [1.19.3, 1.20)
 * See:
 * - {@link MinecraftClientMixin} in subproject 1.15.2-fabric for implementation for mc (~, 1.19.3)
 * - {@link IconsMixin} in subproject 1.20.1-fabric for implementation for mc [1.20, ~)
 */
@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin
{
	@ModifyArg(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lcom/mojang/blaze3d/platform/Window;setIcon(Lnet/minecraft/server/packs/resources/IoSupplier;Lnet/minecraft/server/packs/resources/IoSupplier;)V"
			),
			index = 0
	)
	private IoSupplier<InputStream> bringTheClassicCraftingTableIconBack_general16x(IoSupplier<InputStream> icon16)
	{
		return ClassicMinecraftIconStorage.getResource("icon_16x16.png");
	}

	@ModifyArg(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lcom/mojang/blaze3d/platform/Window;setIcon(Lnet/minecraft/server/packs/resources/IoSupplier;Lnet/minecraft/server/packs/resources/IoSupplier;)V"
			),
			index = 1
	)
	private IoSupplier<InputStream> bringTheClassicCraftingTableIconBack_general32x(IoSupplier<InputStream> icon32)
	{
		return ClassicMinecraftIconStorage.getResource("icon_32x32.png");
	}

	@ModifyArg(
			method = "<init>",
			at = @At(
					value = "INVOKE",
					target = "Lcom/mojang/blaze3d/platform/MacosUtil;loadIcon(Lnet/minecraft/server/packs/resources/IoSupplier;)V"
			)
	)
	private IoSupplier<InputStream> bringTheClassicCraftingTableIconBack_mac(IoSupplier<InputStream> iconMac)
	{
		return ClassicMinecraftIconStorage.getResource("minecraft.icns");
	}
}
