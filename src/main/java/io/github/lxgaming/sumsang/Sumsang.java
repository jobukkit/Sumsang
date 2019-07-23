/*
 * Copyright 2019 Alex Thomson
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

import io.github.lxgaming.sumsang.configuration.Configuration;
import io.github.lxgaming.sumsang.listener.RegistryListener;
import io.github.lxgaming.sumsang.util.Reference;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = Reference.ID)
public class Sumsang {
    
    private static Sumsang instance;
    private final Logger logger = LogManager.getLogger(Reference.NAME);
    private final Configuration configuration = new Configuration();
    
    public Sumsang() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(new RegistryListener());
        
        FMLJavaModLoadingContext.get().getModEventBus().register(getConfiguration());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Sumsang.getInstance().getConfiguration().getSpec());
        
        StartupMessageManager.addModMessage(String.format("%s v%s Initialized", Reference.NAME, Reference.VERSION));
        getLogger().info("{} v{} Initialized", Reference.NAME, Reference.VERSION);
    }
    
    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        StartupMessageManager.addModMessage(String.format("%s v%s Setup", Reference.NAME, Reference.VERSION));
        getLogger().info("{} v{} Setup", Reference.NAME, Reference.VERSION);
    }
    
    public static Sumsang getInstance() {
        return instance;
    }
    
    public Logger getLogger() {
        return logger;
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }
}