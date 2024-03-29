# SplitEditText

[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/SplitEditText/master/app/release/app-release.apk)
[![JCenter](https://img.shields.io/badge/JCenter-1.0.0-46C018.svg)](https://bintray.com/beta/#/jenly/maven/splitedittext)
[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314/splitedittext)](https://repo1.maven.org/maven2/com/github/jenly1314/splitedittext)
[![JitPack](https://jitpack.io/v/jenly1314/SplitEditText.svg)](https://jitpack.io/#jenly1314/SplitEditText)
[![CI](https://travis-ci.org/jenly1314/SplitEditText.svg?branch=master)](https://travis-ci.org/jenly1314/SplitEditText)
[![CircleCI](https://circleci.com/gh/jenly1314/SplitEditText.svg?style=svg)](https://circleci.com/gh/jenly1314/SplitEditText)
[![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)

SplitEditText for Android 是一个灵活的分割编辑框。常常应用于 **验证码输入** 、**密码输入** 、等场景。

## 特性说明
- [x] 支持设置框数量
- [x] 支持设置框的风格样式
- [x] 支持根据状态区分框颜色
- [x] 基于EditText实现，更优雅

## Gif 展示
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

2. 在Module的 **build.gradle** 里面添加引入依赖项

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

#### [CodeTextField](https://github.com/jenly1314/CodeTextField) 一个使用 Compose 实现的验证码输入框
#### [KingKeyboard](https://github.com/jenly1314/KingKeyboard) 自定义键盘，满足各种不同场景的键盘输入需求

## 版本记录

#### v1.1.0：2023-1-9 (开始发布至MavenCentral)
*  迁移发布至MavenCentral

#### v1.0.0：2021-1-5
*  SplitEditText初始版本

## 赞赏
如果您喜欢SplitEditText，或感觉SplitEditText帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:
<p>您也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:

<div>
   <img src="https://jenly1314.github.io/image/page/rewardcode.png">
</div>

## 关于我

| 我的博客                                                                                | GitHub                                                                                  | Gitee                                                                                  | CSDN                                                                                 | 博客园                                                                            |
|:------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------|
| <a title="我的博客" href="https://jenly1314.github.io" target="_blank">Jenly's Blog</a> | <a title="GitHub开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a> | <a title="Gitee开源项目" href="https://gitee.com/jenly1314" target="_blank">jenly1314</a>  | <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>  | <a title="博客园" href="https://www.cnblogs.com/jenly" target="_blank">jenly</a>  |

## 联系我

| 微信公众号        | Gmail邮箱                                                                          | QQ邮箱                                                                              | QQ群                                                                                                                       | QQ群                                                                                                                       |
|:-------------|:---------------------------------------------------------------------------------|:----------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| [Jenly666](http://weixin.qq.com/r/wzpWTuPEQL4-ract92-R) | <a title="给我发邮件" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314</a> | <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314</a> | <a title="点击加入QQ群" href="https://qm.qq.com/cgi-bin/qm/qr?k=6_RukjAhwjAdDHEk2G7nph-o8fBFFzZz" target="_blank">20867961</a> | <a title="点击加入QQ群" href="https://qm.qq.com/cgi-bin/qm/qr?k=Z9pobM8bzAW7tM_8xC31W8IcbIl0A-zT" target="_blank">64020761</a> |

<div>
   <img src="https://jenly1314.github.io/image/page/footer.png">
</div>

