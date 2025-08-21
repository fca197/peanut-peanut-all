# 框架生成代码介绍

| 类                                  | 说明                                              |
|------------------------------------|-------------------------------------------------|
| api/..Api                          | 对外接口 ,可以提出api+entity当此项目的对外API接口                |
| api/impl/..ApiImpl                 | 对外接口实现类                                         |
| api/impl/listener/..ImportListener | excel导入处理类 ,lombok 注解@Accessors(chain=true)禁止使用 |
| mapper/..Mapper                    | mybatis 生成的mapper                               |
| model/..                           | 数据库对应实体                                         |
| service/..                         | 逻辑处理层接口                                         |
| service/impl/..Impl                | 逻辑处理层接口实现类                                      |
