plugins {
    `maven-publish`
    alias(catalog.plugins.idea.ext)

    alias(catalog.plugins.kotlin.jvm)
    alias(catalog.plugins.kotlin.plugin.serialization)

    alias(catalog.plugins.fabric.loom)

    alias(catalog.plugins.explosion)
}

val id: String by rootProject.properties
val name: String by rootProject.properties
val author: String by rootProject.properties
val description: String by rootProject.properties

base { archivesName = rootProject.base.archivesName }

java {
    toolchain { languageVersion = JavaLanguageVersion.of(17) }

    // Still required by IDEs such as Eclipse and Visual Studio Code
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build"
    // task if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    // If this mod is going to be a library, then it should also generate Javadocs in order to
    // aid with development.
    // Uncomment this line to generate them.
    withJavadocJar()
}

dependencies {
    minecraft(catalog.minecraft.fabric)
    mappings(variantOf(catalog.mapping.yarn) { classifier("v2") })

    include(project(":versions:1.20.1"))
    include(project(":versions:1.21"))
}

val metadata =
    mapOf(
        "group" to rootProject.group,
        "author" to author,
        "id" to id,
        "name" to name,
        "version" to version,
        "description" to description,
        "source" to "https://github.com/SettingDust/ReputationViewer",
        "minecraft" to ">=1.20",
        "fabric_loader" to ">=0.15",
        "fabric_kotlin" to ">=1.11",
        "modmenu" to "*",
    )

tasks {
    withType<ProcessResources> {
        inputs.properties(metadata)
        filesMatching(listOf("fabric.mod.json", "*.mixins.json", "waila_plugins.json")) {
            expand(metadata)
        }
    }

    named<Jar>("sourcesJar") {
        from(rootProject.allprojects.map { it.sourceSets.main.get().allSource })
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
