package settingdust.reputationviewer

import net.minecraft.util.Identifier

object GossipHolderProvider {
    @JvmField val IDENTIFIER: Identifier = ReputationViewer.identifier("reputation")

    @JvmField val KEY: String = ReputationViewer.identifier("reputation").toString()

    @JvmField val TOOLTIP_TRANSLATION = "$KEY.tooltip"
}
