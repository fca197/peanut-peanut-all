# 项目结构

## 常用函数
 文件地址：[common-js.ts](../src/common/utils/common-js.ts)


## vue 文件结构

> view
> > aps/base
> > > 模块名称 (表名)  
> > > > 模块名称  *AddEditForm.vue 添加或修改页面  
> > > > 模块名称  *Index.vue 列表页面，支持table展示与查询  
> > > > 模块名称  *Type.ts 数据结构定义， 可以增加通用查询或修改询接口  

```text
  文件生产建议使用模板生产，可以保持代码一致性， 修改非 input 框字段即可
```

# 菜单权限

> 目前显示所有菜单，按角色显示请删除： src/pinia/stores/permission.ts , 第八行： return true;
> 目前已隐藏菜单： 登录账户 ， 角色组信息 ， 角色信息  , 文件目录 src/router/menuList.tsx， 删除  hidden: true 配置即可  
 
# 本地代理

> 修改本地文件： vite.config.ts 中反向代理配置： vite.config.ts:42

# 订单

> 订单相关目录： src/view/aps/ApsOrder*


# 百度统计
>  文件修改： [baiduAnalytics.js](../src/common/utils/baiduAnalytics.js)  
>> [main.ts](../src/main.ts)  // 初始化百度统计（替换为你的 ID）  
>> initBaiduAnalytics("74466160c005f4fe5c7c40b4e1c06610");  
>>[guard.ts](../src/router/guard.ts)  添加当前页面到百度统计 router.afterEach 方法中  trackPageView(to.fullPath)  
 
# 数据库还原
> 文件修改： src/pages/dashboard/index.vue ， 删除 onMounted 方法

# 帮助视频
> 视频配置地址： src/common/components/VideoPlayer/index.ts  
> 匹配逻辑修改： src/common/components/VideoPlayer/index.vue ,playVideoFun 方法中if(currentPath.startsWith(key)) {

# 版本提示  
> 文件修改： [index.vue](../src/pages/login/index.vue)， 方法： onMounted  
