# SplitEditText

[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314/splitedittext?logo=sonatype)](https://repo1.maven.org/maven2/com/github/jenly1314/SplitEditText)
[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/SplitEditText?logo=jitpack)](https://jitpack.io/#jenly1314/SplitEditText)
[![CI](https://img.shields.io/github/actions/workflow/status/jenly1314/SplitEditText/gradle.yml?logo=github)](https://github.com/jenly1314/SplitEditText/actions/workflows/gradle.yml)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/SplitEditText/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/SplitEditText?logo=open-source-initiative)](https://opensource.org/licenses/mit)


SplitEditText for Android 是一个灵活的分割编辑框。常常应用于 **验证码输入** 、**密码输入** 、等场景。

## 特性说明
- ✅ 支持设置框数量
- ✅ 支持设置框的风格样式
- ✅ 支持根据状态区分框颜色
- ✅ 基于EditText实现，更优雅

## 效果展示
![Image](GIF.gif)

> 你也可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/SplitEditText/master/app/release/app-release.apk) 体验效果

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项

    ```gradle
    // AndroidX 版本
    implementation 'com.github.jenly1314:splitedittext:1.1.0'
    ```
   
## 使用

### SplitEditText 自定义属性说明
| 属性 | 值类型 | 默认值 | 说明 |
| :------| :------ | :------ | :------ |
| setStrokeWidth | dimension | 1dp | 画笔描边的宽度 |
| setBorderColor | color |<font color=#666666>#FF666666</font>| 边框颜色 |
| setInputBorderColor | color |<font color=#1E90FF>#FF1E90FF</font>| 已输入文本的边框颜色 |
| setFocusBorderColor | color | | 焦点框的边框颜色 |
| setBoxBackgroundColor | color | | 框的背景颜色 |
| setBorderCornerRadius | dimension | 0dp | 框的圆角大小（当 **BorderSpacing** 为 **0dp** 时，只有最左和最右两端的框有圆角） |
| setBorderSpacing | dimension | 8dp | 框与框之间的间距大小 |
| setMaxLength | integer | 6 | 允许输入的最大长度（框个数量） |
| setBorderStyle | enum | box | 边框风格 |
| setTextStyle | enum | plain_text | 文本风格（可以是明文或者密文，默认：明文） |
| setCipherMask | string | * | 密文掩码（当 **TextStyle** 为密文时，可自定义密文掩码） |
| setFakeBoldText | boolean | false | 是否是粗体 |

### 示例

布局示例
```Xml
    <com.king.view.splitedittext.SplitEditText
        android:id="@+id/splitEditText"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:inputType="number"/>
```

代码示例

Kotlin
```kotlin
    //设置监听
    splitEditText.setOnTextInputListener(object : SplitEditText.OnTextInputListener {
        override fun onTextInputChanged(text: String, length: Int) {
            //TODO 文本输入改变
        }

        override fun onTextInputCompleted(text: String) {
            //TODO 文本输入完成
        }

    })

```

Java
```java
    //设置监听
    splitEditText.setOnTextInputListener(new SplitEditText.OnTextInputListener(){

        @Override
        public void onTextInputChanged(String text, int length) {
            //TODO 文本输入改变
        }

        @Override
        public void onTextInputCompleted(String text) {
            //TODO 文本输入完成
        }
    });

```

更多使用详情，请查看[app](app)中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/SplitEditText/latest/javadoc/)

## 相关推荐

- [CodeTextField](https://github.com/jenly1314/CodeTextField) 一个使用 Compose 实现的验证码输入框。
- [KingKeyboard](https://github.com/jenly1314/KingKeyboard) 自定义键盘，满足各种不同场景的键盘输入需求。
- [compose-component](https://github.com/jenly1314/compose-component) 一个Jetpack Compose的组件库；主要提供了一些小组件，便于快速使用。

## 版本记录

#### v1.1.0：2023-1-9 (开始发布至MavenCentral)
*  迁移发布至MavenCentral

#### v1.0.0：2021-1-5
*  SplitEditText初始版本

---

![footer](https://jenly1314.github.io/page/footer.svg)
