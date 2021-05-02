/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambDynamicLights.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdynlights.mixin;

import me.lambdaurora.lambdynlights.DynamicLightSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientWorld.class)
public class ClientWorldMixin
{
    @Inject(method = "removeEntity", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onFinishRemovingEntity(int entityId, Entity.RemovalReason removalReason, CallbackInfo ci, Entity entity)
    {
        if (entity != null) {
            DynamicLightSource dls = (DynamicLightSource) entity;
            dls.setDynamicLightEnabled(false);
        }
    }
}
