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

package io.github.lxgaming.sumsang;

import io.github.lxgaming.sumsang.configuration.Config;
import io.github.lxgaming.sumsang.init.SumsangAchievements;
import io.github.lxgaming.sumsang.init.SumsangItems;
import io.github.lxgaming.sumsang.proxy.CommonProxy;
import io.github.lxgaming.sumsang.util.LogManager;
import io.github.lxgaming.sumsang.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = Reference.MOD_ID,
	name = Reference.MOD_NAME,
	version = Reference.VERSION,
	acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS,
	acceptableRemoteVersions = Reference.ACCEPTABLE_REMOTE_VERSIONS,
	certificateFingerprint = Reference.CERTIFICATE_FINGERPRINT
)
public class Sumsang {
	
	@Mod.Instance
	private static Sumsang instance;
	
	@SidedProxy(
		clientSide = Reference.CLIENT_PROXY_CLASS,
		serverSide = Reference.SERVER_PROXY_CLASS
	)
	private static CommonProxy proxy;
	
	private Config config;
	private SumsangItems sumsangItems;
	private SumsangAchievements sumsangAchievements;
	
	public Sumsang() {
		this.config = new Config();
		this.sumsangItems = new SumsangItems();
		this.sumsangAchievements = new SumsangAchievements();
	}
	
	@Mod.EventHandler
	public void fingerprintViolation(FMLFingerprintViolationEvent event) {
		LogManager.fatal("Certificate Fingerprint Violation Detected!");
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		getConfig().loadConfig(event.getSuggestedConfigurationFile());
		getSumsangItems().init();
		getSumsangItems().register();
		getSumsangAchievements().init();
		getSumsangAchievements().register();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		getProxy().init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	public static Sumsang getInstance() {
		return instance;
	}
	
	public static CommonProxy getProxy() {
		return proxy;
	}
	
	public Config getConfig() {
		return this.config;
	}
	
	public SumsangItems getSumsangItems() {
		return this.sumsangItems;
	}
	
	public SumsangAchievements getSumsangAchievements() {
		return this.sumsangAchievements;
	}
}