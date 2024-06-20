package settingdust.reputationviewer.mixin.jade;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import settingdust.reputationviewer.GossipHolder;
import settingdust.reputationviewer.GossipHolderProvider;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

@Mixin(value = GossipHolderProvider.class, remap = false)
public class GossipHolderProviderMixin implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
    @Shadow
    @Final
    private static Identifier IDENTIFIER;

    @Shadow
    @Final
    private static String KEY;

    @Shadow
    @Final
    private static String TOOLTIP_TRANSLATION;

    @Override
    public void appendTooltip(final ITooltip tooltip, final EntityAccessor accessor, final IPluginConfig config) {
        var tag = accessor.getServerData();
        var player = accessor.getPlayer();
        var entity = (GossipHolder) accessor.getEntity();
        tooltip.add(Text.translatable(
            TOOLTIP_TRANSLATION,
            tag.contains(KEY)
            ? tag.getInt(KEY)
            : entity.getSelfishvillager$gossips().getReputationFor(player.getUuid(), (it) -> true)
        ));
    }

    @Override
    public void appendServerData(final NbtCompound tag, final EntityAccessor accessor) {
        var player = accessor.getPlayer();
        var entity = (GossipHolder) accessor.getEntity();
        var reputation = entity.getSelfishvillager$gossips().getReputationFor(player.getUuid(), (it) -> true);
        tag.putInt(KEY, reputation);
    }

    @Override
    public Identifier getUid() {
        return IDENTIFIER;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.BODY + 100;
    }
}
