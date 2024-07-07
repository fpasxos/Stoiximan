plugins {
    alias(libs.plugins.stoiximan.android.library)
    alias(libs.plugins.stoiximan.android.room)
}

android {
    namespace = "com.fps.core.database"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}