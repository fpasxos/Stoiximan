plugins {
    alias(libs.plugins.stoiximan.android.library)
    alias(libs.plugins.stoiximan.jvm.ktor)
}

android {
    namespace = "com.fps.core.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}