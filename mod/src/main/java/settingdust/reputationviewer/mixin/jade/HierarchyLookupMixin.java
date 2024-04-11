package settingdust.reputationviewer.mixin.jade;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.impl.HierarchyLookup;

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
                                    "Lsnownee/jade/impl/HierarchyLookup;getInternal(Ljava/lang/Class;Ljava/util/List;)V"))
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
                                    "Lsnownee/jade/impl/HierarchyLookup;getInternal(Ljava/lang/Class;Ljava/util/List;)V"))
    private boolean reputationviewer$checkSuperclass(
            final HierarchyLookup instance, final Class<?> clazz, final List list) {
        return clazz != null;
    }
}
