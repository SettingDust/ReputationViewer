import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val id: String by rootProject.properties
val name: String by rootProject.properties
val author: String by rootProject.properties
val description: String by rootProject.properties

java {
    toolchain { languageVersion = JavaLanguageVersion.of(21) }

    // Still required by IDEs such as Eclipse and Visual Studio Code
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build"
    // task if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    // If this mod is going to be a library, then it should also generate Javadocs in order to
    // aid with development.
    // Uncomment this line to generate them.
    withJavadocJar()
}

loom {
    mixin {
        defaultRefmapName = "$id.refmap.json"

        add("main", "$id.refmap.json")
    }

//    accessWidenerPath = file("src/main/resources/$id.1.20.1.accesswidener")

    mods {
        register(id) {
            sourceSet(rootProject.sourceSets["main"])
        }
    }
}

dependencies {
    minecraft(catalog.minecraft.fabric.get1().get21())
    mappings(variantOf(catalog.mapping.yarn.get1().get21()) { classifier("v2") })

    implementation(project(":")) {
        isTransitive = false
    }

    modImplementation(catalog.fabric.loader)
    modImplementation(catalog.fabric.api.get1().get21())
    modImplementation(catalog.fabric.kotlin)

    modImplementation(catalog.modmenu.get1().get21())

    modImplementation(explosion.fabric(catalog.guard.villagers.get1().get21()))

    modImplementation(catalog.jade.get1().get21())

    modImplementation(catalog.wthit.get1().get21())
    modRuntimeOnly(catalog.badpackets.get1().get21())
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
        "minecraft" to "~1.21",
        "fabric_loader" to ">=0.15",
        "fabric_kotlin" to ">=1.11",
        "modmenu" to "*",
    )

tasks {
    withType<ProcessResources> {
        from(rootProject.sourceSets.main.get().resources)
        inputs.properties(metadata)
        filesMatching(listOf("fabric.mod.json", "*.mixins.json", "waila_plugins.json")) { expand(metadata) }
    }

    withType<JavaCompile> {
        source += rootProject.sourceSets.main.get().java
    }

    withType<KotlinCompile> {
        source(rootProject.sourceSets.main.get().kotlin)
    }

    ideaSyncTask { enabled = true }
}
