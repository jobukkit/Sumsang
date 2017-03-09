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

package io.github.lxgaming.sumsang.configuration;

import java.io.File;

import io.github.lxgaming.sumsang.util.LogHelper;
import net.minecraftforge.common.config.Configuration;

public class Config {
	
	private Configuration configuration;
	
	private boolean rightClickDetonator;
	private int explosivePower;
	private boolean poisonOnDetonate;
	private int poisonTime;
	
	public void loadConfig(File file) {
		this.configuration = new Configuration(file);
		getConfiguration().load();
		
		this.rightClickDetonator = getConfiguration().getBoolean("rightClickDetonator", Configuration.CATEGORY_GENERAL, true, "Explode on right click?");
		this.explosivePower = getConfiguration().getInt("explosivePower", Configuration.CATEGORY_GENERAL, 20, 10, 10000, "Explosive Power.");
		this.poisonOnDetonate = getConfiguration().getBoolean("poisonOnDetonate", Configuration.CATEGORY_GENERAL, true, "Poison on detonate?");
		this.poisonTime = getConfiguration().getInt("poisonTime", Configuration.CATEGORY_GENERAL, 5, 1, 1000, "Poison time in seconds.");
		
		getConfiguration().save();
		LogHelper.info("Configuration file successfully loaded.");
	}
	
	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	public boolean isRightClickDetonator() {
		return this.rightClickDetonator;
	}
	
	public int getExplosivePower() {
		return this.explosivePower;
	}
	
	public boolean isPoisonOnDetonate() {
		return this.poisonOnDetonate;
	}
	
	public int getPoisonTime() {
		return this.poisonTime;
	}
}