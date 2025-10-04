// Top-level build file where you can add configuration options common to all sub-projects/modules.

// 定义项目全局的构建脚本和依赖仓库：
plugins {
    alias(libs.plugins.androidApplication) apply false
}

//// 仓库配置（依赖下载来源）
//allprojects {
//    repositories {
//        google()  // Google仓库（必需，包含AndroidX等官方库）
//        mavenCentral()  // 中央仓库（第三方库常用）
//    }
//}