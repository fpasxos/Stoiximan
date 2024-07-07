@Suppress("DSL_SCOPE_VIOLATION") //
plugins {
    `kotlin-dsl`
}

group = "com.fps.stoiximan.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

// we sort of 'register' all the convention plugins here
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "stoiximan.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "stoiximan.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "stoiximan.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "stoiximan.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "stoiximan.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "stoiximan.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "stoiximan.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmKtor") {
            id = "stoiximan.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
        register("jvmJunit5") {
            id = "stoiximan.jvm.junit5"
            implementationClass = "JvmJUnit5ConventionPlugin"
        }
        register("androidJunit5") {
            id = "stoiximan.android.junit5"
            implementationClass = "AndroidJUnit5ConventionPlugin"
        }
    }
}