package settingdust.reputationviewer.mixin.wthit;

import mcp.mobius.waila.api.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import settingdust.reputationviewer.GossipHolder;
import settingdust.reputationviewer.GossipHolderProvider;

@Mixin(value = GossipHolderProvider.class, remap = false)
public class GossipHolderProviderMixin implements IEntityComponentProvider, IDataProvider<GossipHolder> {
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
    public void appendBody(final ITooltip tooltip, final IEntityAccessor accessor, final IPluginConfig config) {
        var data = accessor.getData().raw();
        var player = accessor.getPlayer();
        var entity = (GossipHolder) accessor.getEntity();

        tooltip.addLine(Text.translatable(
            TOOLTIP_TRANSLATION,
            data.contains(KEY)
            ? data.getInt(KEY)
            : entity.getSelfishvillager$gossips().getReputationFor(player.getUuid(), (it) -> true)
        ));
    }

    @Override
    public void appendData(
        final IDataWriter data, final IServerAccessor<GossipHolder> accessor, final IPluginConfig config
    ) {
        var player = accessor.getPlayer();
        var target = accessor.getTarget();

        data.raw().putInt(KEY, target.getSelfishvillager$gossips().getReputationFor(player.getUuid(), (it) -> true));
    }
}
