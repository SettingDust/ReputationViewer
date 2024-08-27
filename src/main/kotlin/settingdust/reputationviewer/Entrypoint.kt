package settingdust.reputationviewer

import mcp.mobius.waila.api.IClientRegistrar
import mcp.mobius.waila.api.ICommonRegistrar
import mcp.mobius.waila.api.IWailaClientPlugin
import mcp.mobius.waila.api.IWailaCommonPlugin
import net.minecraft.entity.Entity
import net.minecraft.util.Identifier
import snownee.jade.api.EntityAccessor
import snownee.jade.api.IEntityComponentProvider
import snownee.jade.api.IServerDataProvider

fun init() {}

object ReputationViewer {
    const val ID = "reputation-viewer"

    @JvmStatic fun identifier(path: String) = Identifier.of(ID, path)!!
}

@snownee.jade.api.WailaPlugin
class ReputationJadePlugin : snownee.jade.api.IWailaPlugin {
    override fun register(registration: snownee.jade.api.IWailaCommonRegistration) {
        registration.registerEntityDataProvider(
            GossipHolderProvider as IServerDataProvider<EntityAccessor>,
            GossipHolder::class.java as Class<out Entity>)
    }

    override fun registerClient(registration: snownee.jade.api.IWailaClientRegistration) {
        registration.registerEntityComponent(
            GossipHolderProvider as IEntityComponentProvider,
            GossipHolder::class.java as Class<out Entity>)
    }
}

class ReputationWthitPlugin : IWailaCommonPlugin, IWailaClientPlugin {
    override fun register(registrar: ICommonRegistrar) {
        registrar.entityData(
            GossipHolderProvider as mcp.mobius.waila.api.IDataProvider<Entity>,
            GossipHolder::class.java,
            100)
    }

    override fun register(registrar: IClientRegistrar) {
        registrar.body(
            GossipHolderProvider as mcp.mobius.waila.api.IEntityComponentProvider,
            GossipHolder::class.java,
        )
    }
}
