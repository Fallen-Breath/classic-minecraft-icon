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

package me.fallenbreath.classicminecrafticon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

//#if MC >= 11903
//$$ import net.minecraft.resource.InputSupplier;
//#endif

public class ClassicMinecraftIconStorage
{
	private static final String RESOURCES_ROOT = "assets/classicminecrafticon/icons/";
	private static final Map<String, byte[]> STORAGE = Maps.newLinkedHashMap();
	private static final List<String> PNG_PATHS = Lists.newArrayList();
	private static boolean inited = false;

	public synchronized static void init()
	{
		if (inited)
		{
			return;
		}
		loadResource("icon_16x16.png", true);
		loadResource("icon_32x32.png", true);
		loadResource("icon_48x48.png", true);
		loadResource("icon_128x128.png", true);
		loadResource("icon_256x256.png", true);
		loadResource("minecraft.icns", false);
		inited = true;
	}

	private static void loadResource(String path, boolean isPng)
	{
		String fullPath = RESOURCES_ROOT + path;
		ClassLoader classLoader = ClassicMinecraftIconStorage.class.getClassLoader();
		try (InputStream stream = classLoader.getResourceAsStream(fullPath))
		{
			if (stream == null)
			{
				throw new IOException("getResourceAsStream failed");
			}
			byte[] data = IOUtils.toByteArray(stream);
			STORAGE.put(path, data);
			if (isPng)
			{
				PNG_PATHS.add(path);
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(String.format("Failed to load resource %s: %s", fullPath, e));
		}
	}

	//#if MC >= 11903
	//$$ public static InputSupplier<InputStream> getResource(String path)
	//#else
	public static InputStream getResource(String path)
	//#endif
	{
		init();
		byte[] data = STORAGE.get(path);
		if (data == null)
		{
			throw new RuntimeException("Unexpected resource path " + path);
		}
		return
				//#if MC >= 11903
				//$$ () ->
				//#endif
				new ByteArrayInputStream(data);
	}

	//#if MC >= 12000
	//$$ public static List<InputSupplier<InputStream>> getAllPngResources()
	//$$ {
	//$$ 	init();
	//$$ 	return PNG_PATHS.stream().
	//$$ 			map(ClassicMinecraftIconStorage::getResource).
	//$$ 			toList();
	//$$ }
	//#endif
}
