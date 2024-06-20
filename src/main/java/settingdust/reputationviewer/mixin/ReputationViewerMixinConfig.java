package settingdust.reputationviewer.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class ReputationViewerMixinConfig implements IMixinConfigPlugin {
    @Override
    public void onLoad(final String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public boolean shouldApplyMixin(final String targetClassName, final String mixinClassName) {
        if (mixinClassName.startsWith("settingdust.reputationviewer.mixin.jade")) {
            return FabricLoader.getInstance().isModLoaded("jade");
        } else if (mixinClassName.startsWith("settingdust.reputationviewer.mixin.wthit"))
            return FabricLoader.getInstance().isModLoaded("wthit");
        return true;
    }

    @Override
    public void acceptTargets(final Set<String> myTargets, final Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(
        final String targetClassName,
        final ClassNode targetClass,
        final String mixinClassName,
        final IMixinInfo mixinInfo
    ) {

    }

    @Override
    public void postApply(
        final String targetClassName,
        final ClassNode targetClass,
        final String mixinClassName,
        final IMixinInfo mixinInfo
    ) {

    }
}
