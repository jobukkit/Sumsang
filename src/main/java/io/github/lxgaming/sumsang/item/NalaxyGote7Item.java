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

package io.github.lxgaming.sumsang.item;

import io.github.lxgaming.sumsang.Sumsang;
import io.github.lxgaming.sumsang.util.Reference;
import io.github.lxgaming.sumsang.util.Toolbox;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class NalaxyGote7Item extends Item {
    
    private static final ResourceLocation DETONATE_ADVANCEMENT = new ResourceLocation(Reference.ID, "detonate");
    private static final ResourceLocation OVER_9000_ADVANCEMENT = new ResourceLocation(Reference.ID, "over_9000");
    
    public NalaxyGote7Item() {
        super(new Properties().group(ItemGroup.MISC).maxStackSize(16));
        setRegistryName(Reference.ID, "nalaxygote7");
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        if (world.isRemote || !Sumsang.getInstance().getConfiguration().getRightClickDetonator().get()) {
            return new ActionResult<>(ActionResultType.PASS, itemStack);
        }
        
        if (!player.isCreative()) {
            itemStack.shrink(1);
        }
        
        float explosivePower = Sumsang.getInstance().getConfiguration().getExplosivePower().get();
        if (explosivePower > 0) {
            Toolbox.grantAdvancement(player, DETONATE_ADVANCEMENT);
            if (explosivePower > 9000) {
                Toolbox.grantAdvancement(player, OVER_9000_ADVANCEMENT);
            }
            
            world.createExplosion(null, player.posX, player.posY, player.posZ, (explosivePower / 10), true, Explosion.Mode.DESTROY);
        }
        
        int poisonTime = Sumsang.getInstance().getConfiguration().getPoisonTime().get();
        if (Sumsang.getInstance().getConfiguration().getPoisonOnDetonate().get() && poisonTime > 0) {
            player.addPotionEffect(new EffectInstance(Effects.POISON, (20 * poisonTime), 0));
        }
        
        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }
    
    
}