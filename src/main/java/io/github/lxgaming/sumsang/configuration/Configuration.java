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

package io.github.lxgaming.sumsang.configuration;

import io.github.lxgaming.sumsang.Sumsang;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class Configuration {
    
    private final ForgeConfigSpec spec;
    private final ForgeConfigSpec.ConfigValue<Integer> explosivePower;
    private final ForgeConfigSpec.ConfigValue<Boolean> poisonOnDetonate;
    private final ForgeConfigSpec.ConfigValue<Integer> poisonTime;
    private final ForgeConfigSpec.ConfigValue<Boolean> rightClickDetonator;
    
    public Configuration() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Common");
        
        this.explosivePower = builder
                .comment("Explosive Power")
                .translation("config.sumsang.explosive_power")
                .define("explosivePower", 20);
        
        this.poisonOnDetonate = builder
                .comment("Poison on detonate?")
                .translation("config.sumsang.poison_on_detonate")
                .define("poisonOnDetonate", true);
        
        this.poisonTime = builder
                .comment("Poison time in seconds")
                .translation("config.sumsang.poison_time")
                .define("poisonTime", 5);
        
        this.rightClickDetonator = builder
                .comment("Explode on right click?")
                .translation("config.sumsang.right_click_detonator")
                .define("rightClickDetonator", true);
        
        builder.pop();
        this.spec = builder.build();
    }
    
    @SubscribeEvent
    public void configLoading(ModConfig.Loading event) {
        Sumsang.getInstance().getLogger().info("Loaded config file {}", event.getConfig().getFileName());
    }
    
    @SubscribeEvent
    public void configReloading(ModConfig.Reloading event) {
        Sumsang.getInstance().getLogger().info("Reloaded config file {}", event.getConfig().getFileName());
    }
    
    public ForgeConfigSpec getSpec() {
        return spec;
    }
    
    public ForgeConfigSpec.ConfigValue<Integer> getExplosivePower() {
        return explosivePower;
    }
    
    public ForgeConfigSpec.ConfigValue<Boolean> getPoisonOnDetonate() {
        return poisonOnDetonate;
    }
    
    public ForgeConfigSpec.ConfigValue<Integer> getPoisonTime() {
        return poisonTime;
    }
    
    public ForgeConfigSpec.ConfigValue<Boolean> getRightClickDetonator() {
        return rightClickDetonator;
    }
}