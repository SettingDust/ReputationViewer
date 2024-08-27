val minecraft = "1.20.1"
extra["minecraft"] = minecraft

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/common.gradle.kts")

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/kotlin.gradle.kts")

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/fabric.gradle.kts")

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/modmenu.gradle.kts")

dependencyResolutionManagement.versionCatalogs.named("catalog") {
    library("minecraft-fabric-1.21", "com.mojang", "minecraft").version("1.21")

    library("jade", "maven.modrinth", "jade").version("11.11.0+fabric")
    library("jade-1.21", "maven.modrinth", "jade").version("15.2.1+fabric")

    library("wthit", "maven.modrinth", "wthit").version("fabric-8.15.0")
    library("wthit-1.21", "maven.modrinth", "wthit").version("fabric-12.4.0")
    library("badpackets", "maven.modrinth", "badpackets").version("fabric-0.4.3")
    library("badpackets-1.21", "maven.modrinth", "badpackets").version("fabric-0.8.1")

    // https://modrinth.com/mod/guard-villagers-(fabricquilt)/versions
    library("guard-villagers", "maven.modrinth", "guard-villagers-(fabricquilt)")
        .version("2.0.9-$minecraft")
    library("guard-villagers-1.21", "maven.modrinth", "guard-villagers-(fabricquilt)")
        .version("2.1.1-1.21")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

val name: String by settings

rootProject.name = name

include("nested")

include("versions")
include("versions:1.21")
include("versions:1.20.1")
