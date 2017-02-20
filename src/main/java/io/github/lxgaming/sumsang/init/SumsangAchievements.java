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

import io.github.lxgaming.sumsang.Sumsang;
import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class SumsangAchievements {
	
	private AchievementPage achievementPage;
	
	private Achievement achievementCraft;
	private Achievement achievementDetonate;
	private Achievement achievementOver9000;
	
	public void init() {
		achievementCraft = new Achievement("achievement.craft", "craft", 0, 0, Sumsang.getInstance().getSumsangItems().itemNalaxyGote7, null);
		achievementDetonate = new Achievement("achievement.detonate", "detonate", 2, 0, Blocks.REDSTONE_TORCH, achievementCraft);
		achievementOver9000 = new Achievement("achievement.over9000", "over9000", 4, 0, Blocks.TNT, achievementDetonate);
		
		achievementPage = new AchievementPage("Sumsang", achievementCraft, achievementDetonate, achievementOver9000);
	}
	
	public void register() {
		achievementCraft.registerStat();
		achievementDetonate.registerStat();
		achievementOver9000.registerStat();
		AchievementPage.registerAchievementPage(achievementPage);
	}
	
	public Achievement getAchievementCraft() {
		return this.achievementCraft;
	}
	
	public Achievement getAchievementDetonate() {
		return this.achievementDetonate;
	}
	
	public Achievement getAchievementOver9000() {
		return this.achievementOver9000;
	}
}