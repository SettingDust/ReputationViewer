package settingdust.reputationviewer.mixin.v1_21.jade;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.impl.lookup.HierarchyLookup;

import java.util.List;

@Mixin(value = HierarchyLookup.class, remap = false)
public abstract class HierarchyLookupMixin {
    @Shadow
    protected abstract void getInternal(final Class<?> clazz, final List<?> list);

    @Inject(
        method = "getInternal",
        at =
        @At(
            value = "INVOKE",
            target =
                "Lsnownee/jade/impl/lookup/HierarchyLookup;getInternal(Ljava/lang/Class;Ljava/util/List;)V"
        )
    )
    private void reputationviewer$checkInterface(final Class<?> clazz, final List<?> list, final CallbackInfo ci) {
        for (final Class<?> clazzInterface : clazz.getInterfaces()) {
            getInternal(clazzInterface, list);
        }
    }

    @WrapWithCondition(
        method = "getInternal",
        at =
        @At(
            value = "INVOKE",
            target =
                "Lsnownee/jade/impl/lookup/HierarchyLookup;getInternal(Ljava/lang/Class;Ljava/util/List;)V"
        )
    )
    private boolean reputationviewer$checkSuperclass(
        final HierarchyLookup instance, final Class<?> clazz, final List list
    ) {
        return clazz != null;
    }

    @ModifyReturnValue(method = "isClassAcceptable", at = @At("RETURN"))
    private boolean reputationviewer$allowInterface(final boolean original, Class<?> clazz) {
        if (!original && clazz.isInterface()) return true;
        return original;
    }
}
