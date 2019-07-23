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

package io.github.lxgaming.sumsang.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class Toolbox {
    
    public static boolean grantAdvancement(PlayerEntity player, ResourceLocation id) {
        if (player instanceof ServerPlayerEntity) {
            return getAdvancement(player.getServer(), id)
                    .map(advancement -> grantAdvancement((ServerPlayerEntity) player, advancement))
                    .orElse(false);
        }
        
        return false;
    }
    
    public static boolean grantAdvancement(ServerPlayerEntity player, Advancement advancement) {
        if (advancement.getCriteria().size() != 1 || !advancement.getCriteria().containsKey("impossible")) {
            return false;
        }
        
        return player.getAdvancements().grantCriterion(advancement, "impossible");
    }
    
    public static Optional<Advancement> getAdvancement(MinecraftServer server, ResourceLocation id) {
        if (server != null && id != null) {
            return Optional.ofNullable(server.getAdvancementManager().getAdvancement(id));
        }
        
        return Optional.empty();
    }
}