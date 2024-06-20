val minecraft = "1.21"
extra["minecraft"] = minecraft

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/common.gradle.kts")

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/kotlin.gradle.kts")

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/fabric.gradle.kts")

apply("https://github.com/SettingDust/MinecraftGradleScripts/raw/main/modmenu.gradle.kts")

dependencyResolutionManagement.versionCatalogs.named("catalog") {
    library("jade", "maven.modrinth", "jade").version("15.0.4+fabric")

    library("wthit", "maven.modrinth", "wthit").version("fabric-12.2.0")
    library("badpackets", "maven.modrinth", "badpackets").version("fabric-0.8.1")

    // https://modrinth.com/mod/guard-villagers-(fabricquilt)/versions
    library("guard-villagers", "maven.modrinth", "guard-villagers-(fabricquilt)")
        .version("2.1.0-$minecraft")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

val name: String by settings

rootProject.name = name
