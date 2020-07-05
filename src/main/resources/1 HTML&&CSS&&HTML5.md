
# HTML 复习查漏补缺笔记

问题：1.布局 2.属性优先级 3.元素种类 4.如何选择输入

<a id="_top"></a>

### <a href="#_1" rel="nofollow" target="_self">基本理论概念</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">文件路径</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">链接和锚点</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">块元素&&内联元素</a>
1.4 <a href="#_1.4" rel="nofollow" target="_self">HTML 头部</a>
1.5 <a href="#_1.5" rel="nofollow" target="_self">HTM5新特性</a>
1.6 <a href="#_1.6" rel="nofollow" target="_self">字符实体</a>
### <a href="#_2" rel="nofollow" target="_self">常用元素</a>
2.1 <a href="#_2.1" rel="nofollow" target="_self">W3C速查手册</a>
2.2 <a href="#_2.2" rel="nofollow" target="_self">HTML5语义化标签</a>
### <a href="#_3" rel="nofollow" target="_self">表单</a>
3.1 <a href="#_3.1" rel="nofollow" target="_self">表单常用属性</a>
3.2 <a href="#_3.2" rel="nofollow" target="_self">`<select>`标签</a>
3.3 <a href="#_3.3" rel="nofollow" target="_self">`<textarea>`标签</a>
3.4 <a href="#_3.4" rel="nofollow" target="_self">`<button>`标签</a>
3.5 <a href="#_3.5" rel="nofollow" target="_self">`<datalist>`预定义选型列表标签(类似于下拉列表)</a>

## `基本理论概念`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_1.1"></a>

**1. 文件路径**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

路径	| 描述
--- | ---
`<img src="picture.jpg">`	| picture.jpg 位于与当前网页相同的文件夹
`<img src="images/picture.jpg">` | picture.jpg 位于当前文件夹的 images 文件夹中
`<img src="/images/picture.jpg">` | picture.jpg 当前站点根目录的 images 文件夹中
`<img src="../picture.jpg">` |	picture.jpg 位于当前文件夹的上一级文件夹中
`<img src="https://www.w3school.com.cn/images/picture.jpg" alt="flower">` | 绝对路径

---

<a id="_1.2"></a>

**2. 链接和锚点**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

2.1 超链接属性介绍

- 通过使用 href 属性 - 创建指向另一个文档的链接
- 通过使用 name 属性 - 创建文档内的书签

2.2 href使用示例

```java
<a href="http://www.w3school.com.cn/" target="_blank">Visit W3School!</a>
```

2.3 name锚点示例

```java
<a href="#buttom">跳转到底部</a>
```

```java
<a name="buttom">底部</a>
```

---

<a id="_1.3"></a>

**3. 块元素&&内联元素**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

3.1 块元素 block level element

- 块计元素在浏览器显示时，通常以新行来开始(和结束)

```java
<h1><p><ul><table><div>
```

3.2 内联元素 inline element

- 内联元素通常不会以新行开始

```java
<b><td><a><img>
```

---

<a id="_1.3"></a>

**3. HTML 头部**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

3.1 `<head>`元素

- 头部元素的容器。可包含脚本，知识浏览器样式链接，提供元信息...

```java
<!-- 可以添加元素 -->
<title>、<base>、<link>、<meta>、<script> 以及 <style>
```

3.2 `<title>`元素

- 定义浏览器工具栏中的标题
- 提供页面被添加到收藏夹时显示的标题
- 显示在搜索引擎结果中的页面标题

```java
<title>Title of the document</title>
```

3.3 `<base>`元素

- 规定页面上所有链接默认的地址或者默认的目标

```java
<head>
<base href="http://www.w3school.com.cn/images/" />
<base target="_blank" />
</head>
```

3.4 `<link>`元素

- 通常链接 CSS 资源

```java
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css" />
</head>
```

3.5 `<style>`元素

- 定义样式

```java
<style type="text/css">
    body {background-color:yellow}
    p {color:blue}
</style>
```

3.6 `<meta>`元素

- 关于数据的信息。meta 元素被用于规定页面的描述、关键词、文档的作者、最后修改时间以及其他元数据。

```java
<meta name="description" content="Free Web tutorials on HTML, CSS, XML" />
```

```java
<meta name="keywords" content="HTML, CSS, XML" />
```

3.7 `<script>`元素

- 页面脚本资源

```java
<script src="js/my.js" />
<script>
    console.log("Hello");
<script>
```

---

<a id="_1.4"></a>

**4. HTM5新特性**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

4.1 文档声明示例

```java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title of the document</title>
</head>

<body>
Content of the document......
</body>

</html>
```

4.2 新特性

- 新的语义元素，比如 `<header>`, `<footer>`, `<article>`, and `<section>`。
- 新的表单控件，比如数字、日期、时间、日历和滑块。
- 强大的图像支持（借由 `<canvas>` 和 `<svg>`）
- 强大的多媒体支持（借由 `<video>` 和 `<audio>`）
- 强大的新 API，比如用本地存储取代 cookie。

---

<a id="_1.5"></a>

**5. 字符实体**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>



符号 | 解释 | HTML对应写法
---|---|---
> | 空格 | &nbsp; &#160;
< | 小于号 | &lt; &#60;
> | 大于号 | &gt; &#62;
& | 和号 | &amp; &#38;
" | 引号 | &quot; &#34;
' | 撇号  | &apos; (IE不支持) | &#39;
￠ | 分（cent） | &cent; &#162;
£ | 镑（pound） | &pound; &#163;
¥ | 元（yen） | &yen; &#165;
€ | 欧元（euro） | &euro; &#8364;
§ | 小节 | &sect; &#167;
© | 版权（copyright） | &copy; &#169;
® | 注册商标 | &reg; &#174;
™ | 商标 | &trade; &#8482;
× | 乘号 | &times; &#215;
÷ | 除号 | &divide; &#247;

---

<a id="_2"></a>

## `常用元素`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

---

<a id="_2.1"></a>

**1. W3C速查手册**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

[基本元素速查手册](https://www.w3school.com.cn/html/html_quick.asp)

[HTML简单实例参考](https://www.w3school.com.cn/example/html_examples.asp)




---

<a id="_2.2"></a>

**2. HTML5语义化标签**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

标签 | 描述
--- | ---
`<article>` | 定义文章。
`<aside>` | 定义页面内容以外的内容。
`<details>` | 定义用户能够查看或隐藏的额外细节。
`<figcaption>` | 定义 `<figure>` 元素的标题。
`<figure>` | 规定自包含内容，比如图示、图表、照片、代码清单等。
`<footer>` | 定义文档或节的页脚。
`<header>` | 规定文档或节的页眉。
`<main>` | 规定文档的主内容。
`<mark>` | 定义重要的或强调的文本。
`<nav>` | 定义导航链接。
`<section>` | 定义文档中的节。
`<summary>` | 定义 `<details>` 元素的可见标题。
`<time>` | 定义日期/时间。

---

<a id="_3"></a>

## `表单`

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

[表单学习参考网址](https://www.w3school.com.cn/html/html_forms.asp)

---

<a id="_3.1"></a>

**1. 表单常用属性**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a>

1.1 通用

name 提交表单所必须的标签属性，用于服务器数据封装，数据获取

1.2 <form>标签使用

action 强求服务器资源路径
method 请求类型，常用 get post
target 是否在新的标签中打开链接，默认 _self 在新标签页中打开

1.3 <input><select>...等其他表单元素使用

type  输入类型，text color date datetime datetime-local email month number range search tel time url week

[限制属性使用示例参考文档](https://www.w3school.com.cn/html/html_form_attributes.asp)

限制属性 | 描述
--- | ---
disabled | 规定输入字段应该被禁用。
max | 规定输入字段的最大值。
maxlength | 规定输入字段的最大字符数。
min | 规定输入字段的最小值。
pattern | 规定通过其检查输入值的正则表达式。
readonly | 规定输入字段为只读（无法修改）。
required | 规定输入字段是必需的（必需填写）。
size | 规定输入字段的宽度（以字符计）。
step | 规定输入字段的合法数字间隔。
value | 规定输入字段的默认值。

---

<a id="_3.2"></a>

**2. `<select>`标签**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a><select>

```java
<select name="cars">
<option value="volvo" selected>Volvo</option>
<option value="saab">Saab</option>
<option value="fiat">Fiat</option>
<option value="audi">Audi</option>
</select>
```

---

<a id="_3.3"></a>

**3. `<textarea>`标签**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a><textarea>

```java
<textarea name="message" rows="10" cols="30">
The cat was playing in the garden.
</textarea>
```

---

<a id="_3.4"></a>

**4. `<button>`标签**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a><button>

```java
<button type="button" onclick="alert('Hello World!')">Click Me!</button>
```

---

<a id="_3.5"></a>

**5. `<datalist>`预定义选型列表标签(类似于下拉列表)**

--- 

- <a href="#_top" rel="nofollow" target="_self">返回目录</a><datalist>

```java
<form action="action_page.php">
<input list="browsers">
<datalist id="browsers">
   <option value="Internet Explorer">
   <option value="Firefox">
   <option value="Chrome">
   <option value="Opera">
   <option value="Safari">
</datalist> 
</form>
```

    