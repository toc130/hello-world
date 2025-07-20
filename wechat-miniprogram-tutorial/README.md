# 微信小程序入门教程

本教程旨在帮助初学者快速入门微信小程序的开发。

## 目录

1. [准备工作](#1-准备工作)
2. [创建第一个小程序](#2-创建第一个小程序)
3. [小程序结构](#3-小程序结构)
4. [基础组件](#4-基础组件)
5. [API 使用](#5-api-使用)
6. [调试和发布](#6-调试和发布)

## 1. 准备工作

在开始之前，你需要：

*   一个微信账号
*   安装 [微信开发者工具](https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html)

## 2. 创建第一个小程序

1.  打开微信开发者工具。
2.  使用你的微信扫码登录。
3.  点击“+”号，选择“小程序”项目。
4.  填写项目名称、目录（选择一个空文件夹）、AppID（如果没有可以点击“测试号”自动获取）。
5.  选择一个模板（例如“JS-基础模板”）。
6.  点击“创建”。

## 3. 小程序结构

你的小程序项目将包含以下文件和文件夹：

*   `app.js`: 小程序的入口文件，包含小程序的生命周期函数。
*   `app.json`: 小程序的全局配置文件，用于配置小程序的页面、窗口表现、网络超时时间等。
*   `app.wxss`: 小程序的全局样式文件。
*   `pages/`: 存放所有页面的文件夹。
    *   `index/`: 首页文件夹。
        *   `index.js`: 页面的逻辑文件。
        *   `index.wxml`: 页面的结构文件（类似于 HTML）。
        *   `index.wxss`: 页面的样式文件。
        *   `index.json`: 页面的配置文件。
*   `sitemap.json`: 用来配置小程序及其页面是否允许被微信索引。

## 4. 基础组件

微信小程序提供了一系列基础组件，用于构建用户界面。

*   **视图容器**: `view`, `scroll-view`, `swiper`
*   **基础内容**: `icon`, `text`, `progress`
*   **表单组件**: `button`, `checkbox`, `form`, `input`, `picker`, `radio`, `slider`, `switch`
*   **导航**: `navigator`
*   **媒体组件**: `image`, `video`, `camera`

**示例：在 `index.wxml` 中使用组件**

```html
<view class="container">
  <text>{{ message }}</text>
  <button bindtap="changeMessage">点击我</button>
</view>
```

**示例：在 `index.js` 中处理逻辑**

```javascript
Page({
  data: {
    message: 'Hello, World!'
  },
  changeMessage: function() {
    this.setData({
      message: '你好，小程序！'
    })
  }
})
```

## 5. API 使用

微信小程序提供了丰富的 API，用于访问微信的各种能力，例如：

*   `wx.request`: 发起网络请求。
*   `wx.login`: 获取用户登录凭证。
*   `wx.getUserInfo`: 获取用户信息。
*   `wx.showToast`: 显示消息提示框。

**示例：发起网络请求**

```javascript
wx.request({
  url: 'https://api.example.com/data',
  success (res) {
    console.log(res.data)
  }
})
```

## 6. 调试和发布

*   **调试**: 使用微信开发者工具的调试器，可以查看 console 日志、网络请求、存储数据等。
*   **预览**: 点击工具栏上的“预览”按钮，用手机微信扫描二维码，可以在真实设备上体验。
*   **发布**:
    1.  点击工具栏上的“上传”按钮，填写版本号和项目备注。
    2.  登录[微信小程序后台](https://mp.weixin.qq.com/)。
    3.  在“版本管理”中，找到你刚上传的版本，点击“提交审核”。
    4.  审核通过后，点击“发布”，你的小程序就可以被所有微信用户搜索和使用了。

---

恭喜你，你已经完成了微信小程序的入门学习！接下来，你可以深入学习更多高级功能，构建出功能更丰富的小程序。
