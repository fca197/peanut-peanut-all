# 项目说明

改项目为所有项目集合， 后续所有开发任务再此开发， 除部分计算方式不开源外，其他均已开源， 详情见各模块 README.MD 文件

# 项目拆解

> 项目可按需引入，也可单独启动， 启动更自由

| 模块名称       | 模块依赖              | 包含内容                        | 
|------------|-------------------|-----------------------------|
| 基础模块       | base-model        | 基础配置， 包含 entity定义和  json序列化 | 
| aps排产排程    | peanut-aps        | 预测，排产排程系统                   | 
| 基础模块       | peanut-base       | 基础配置                        | 
| 文档模块       | peanut-doc        | 项目文档                        |  
| 工作流        | peanut-flow       | 工作流相关， 暂停                   | 
| 网关         | peanut-gateway    | 前端网关,集成每个单独模块               | 
| java 模块脚手架 | peanut-java-model | java 模块脚手架                  | 
| 基础配置       | peanut-sdk        | 项目通用方法，配置等                  | 
| 任务         | peanut-task       | 自定义工作流， 暂停                  | 
| aps web端   | peanut-web-aps    | aps web端                    | 
| web端脚手架    | peanut-web-base   | web端  脚手架                   | 


