/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.sumsang.init;

import io.github.lxgaming.sumsang.items.ItemNalaxyGote7;
import io.github.lxgaming.sumsang.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SumsangItems {
	
	public ItemNalaxyGote7 itemNalaxyGote7;
	
	public void init() {
		itemNalaxyGote7 = new ItemNalaxyGote7();
	}
	
	public void register() {
		GameRegistry.register(itemNalaxyGote7);
	}
	
	public void registerRecipes() {
		itemNalaxyGote7.registerRecipe();
	}
	
	public void registerRenders() {
		registerRender(itemNalaxyGote7);
	}
	
	public void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}