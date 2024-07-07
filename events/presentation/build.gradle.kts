plugins {
    alias(libs.plugins.stoiximan.android.feature.ui)
    alias(libs.plugins.stoiximan.jvm.junit5)
}

android {
    namespace = "com.fps.events.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.compose.svg)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.events.domain)

//    implementation(libs.junit5.api)
    implementation(libs.coroutines.test)
}