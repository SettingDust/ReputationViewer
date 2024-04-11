package settingdust.reputationviewer

import net.minecraft.entity.Entity
import net.minecraft.util.Identifier
import snownee.jade.api.EntityAccessor
import snownee.jade.api.IEntityComponentProvider
import snownee.jade.api.IServerDataProvider

fun init() {}

object ReputationViewer {
    const val ID = "reputation-viewer"

    @JvmStatic fun identifier(path: String) = Identifier(ID, path)
}

@snownee.jade.api.WailaPlugin
class ReputationJadePlugin : snownee.jade.api.IWailaPlugin {
    override fun register(registration: snownee.jade.api.IWailaCommonRegistration) {
        registration.registerEntityDataProvider(
            GossipHolderProvider as IServerDataProvider<EntityAccessor>,
            GossipHolder::class.java as Class<out Entity>
        )
    }

    override fun registerClient(registration: snownee.jade.api.IWailaClientRegistration) {
        registration.registerEntityComponent(
            GossipHolderProvider as IEntityComponentProvider,
            GossipHolder::class.java as Class<out Entity>
        )
    }
}

class ReputationWthitPlugin : mcp.mobius.waila.api.IWailaPlugin {
    override fun register(registrar: mcp.mobius.waila.api.IRegistrar) {
        // Client Side
        registrar.addComponent(
            GossipHolderProvider as mcp.mobius.waila.api.IEntityComponentProvider,
            mcp.mobius.waila.api.TooltipPosition.BODY,
            GossipHolder::class.java,
        )

        // Server Side
        registrar.addEntityData(
            GossipHolderProvider as mcp.mobius.waila.api.IDataProvider<Entity>,
            GossipHolder::class.java,
            100
        )
    }
}
