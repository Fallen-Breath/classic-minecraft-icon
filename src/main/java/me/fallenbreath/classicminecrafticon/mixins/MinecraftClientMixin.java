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
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//#if MC >= 11902
//$$ import org.spongepowered.asm.mixin.injection.ModifyArg;
//#endif

/**
 * The implementation for mc (~, 1.20)
 * See {@link IconsMixin} in subproject 1.20 for implementation for mc [1.20, ~)
 */
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
	@ModifyArgs(
			//#if MC >= 11500
			method = "<init>",
			//#else
			//$$ method = "init",
			//#endif
			at = @At(
					value = "INVOKE",
					//#if MC >= 11903
					//$$ target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/InputSupplier;Lnet/minecraft/resource/InputSupplier;)V"
					//#else
					target = "Lnet/minecraft/client/util/Window;setIcon(Ljava/io/InputStream;Ljava/io/InputStream;)V"
					//#endif
			)
	)
	private void bringTheClassicCraftingTableIconBack_general(Args args)
	{
		args.set(0, ClassMinecraftIconStorage.getResource("icon_16x16.png"));
		args.set(1, ClassMinecraftIconStorage.getResource("icon_32x32.png"));
	}

	//#if MC >= 11902
	//$$ @ModifyArgs(
	//$$ 		method = "<init>",
	//$$ 		at = @At(
	//$$ 				value = "INVOKE",
						//#if MC >= 11903
						//$$ target = "Lnet/minecraft/client/util/MacWindowUtil;setApplicationIconImage(Lnet/minecraft/resource/InputSupplier;)V"
						//#else
						//$$ target = "Lnet/minecraft/client/util/MacWindowUtil;setApplicationIconImage(Ljava/io/InputStream;)V"
						//#endif
	//$$ 		)
	//$$ )
	//$$ private void bringTheClassicCraftingTableIconBack_mac(Args args)
	//$$ {
	//$$ 	args.set(0, ClassMinecraftIconStorage.getResource("minecraft.icns"));
	//$$ }
	//#endif
}
