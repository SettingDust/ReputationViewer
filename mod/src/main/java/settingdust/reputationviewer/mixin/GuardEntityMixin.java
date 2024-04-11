package settingdust.reputationviewer.mixin;

import dev.sterner.guardvillagers.common.entity.GuardEntity;
import net.minecraft.village.VillagerGossips;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import settingdust.reputationviewer.GossipHolder;

@Mixin(GuardEntity.class)
public class GuardEntityMixin implements GossipHolder {

    @Shadow
    @Final
    private VillagerGossips gossips;

    @NotNull
    @Override
    public VillagerGossips getSelfishvillager$gossips() {
        return gossips;
    }
}
