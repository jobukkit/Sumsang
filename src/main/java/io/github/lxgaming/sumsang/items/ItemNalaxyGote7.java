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

package io.github.lxgaming.sumsang.items;

import io.github.lxgaming.sumsang.Sumsang;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemNalaxyGote7 extends Item {
	
	public ItemNalaxyGote7() {
		setUnlocalizedName("nalaxygote7");
		setRegistryName("NalaxyGote7");
		setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		entityPlayer.addStat(Sumsang.getInstance().getSumsangAchievements().getAchievementCraft(), 1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
		ItemStack itemStack = entityPlayer.getHeldItem(enumHand);
		if (!Sumsang.getInstance().getConfig().isRightClickDetonator() || world.isRemote) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
		}
		
		if (!entityPlayer.capabilities.isCreativeMode) {
			itemStack.shrink(1);
		}
		
		world.createExplosion(null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, (Sumsang.getInstance().getConfig().getExplosivePower() / 10), true);
		if (Sumsang.getInstance().getConfig().isPoisonOnDetonate()) {
			entityPlayer.addPotionEffect(new PotionEffect(Potion.getPotionById(19), (20 * Sumsang.getInstance().getConfig().getPoisonTime()), 0));
		}
		
		entityPlayer.addStat(Sumsang.getInstance().getSumsangAchievements().getAchievementDetonate(), 1);
		if (Sumsang.getInstance().getConfig().getExplosivePower() > 9000) {
			entityPlayer.addStat(Sumsang.getInstance().getSumsangAchievements().getAchievementOver9000(), 1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
	}
	
	public void registerRecipe() {
		GameRegistry.addRecipe(new ItemStack(this, 3),
				"III",
				"IGI",
				"IRI",
				'I', Items.IRON_INGOT, 'G', Items.GOLD_INGOT, 'R', Items.REDSTONE);
	}
}