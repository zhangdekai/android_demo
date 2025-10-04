// 这是 Java 项目最核心的配置文件，定义编译选项、依赖等：

plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.android_demo" // 与包名一致
    compileSdk = 34 // 编译时使用的Android SDK版本（推荐最新稳定版）

    defaultConfig {
        applicationId = "com.example.android_demo" // 应用唯一标识（上架Google Play必需）
        minSdk = 26  // 最低支持的Android版本
        targetSdk = 34 // 目标适配的Android版本（建议与compileSdk一致）
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    // 配置签名信息（发布版）
//    signingConfigs {
//        release {
//            storeFile file("my-release-key.jks")  // 签名文件路径
//            storePassword "your_store_password"
//            keyAlias "your_key_alias"
//            keyPassword "your_key_password"
//        }
//    }
    buildTypes {
        release {
            isMinifyEnabled = false //// 是否启用代码混淆（发布版建议设为true）
//            signingConfig signingConfigs.release  // 关联签名配置
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    // Java编译配置
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Java源代码版本
        targetCompatibility = JavaVersion.VERSION_1_8 // 生成的字节码版本
    }
    buildFeatures {
        viewBinding = true
    }
}

// 依赖配置（第三方库、AndroidX组件等）
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}