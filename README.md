# 《毛概》课程网站
## 技术架构
| 框架                 | 版本    |
| -------------------- | ------- |
| JDK                  | v1.8    |
| springboot           | v2.2.4  |
| mybatis              | v1.2.13 |
| shiro                | v1.4.0  |
| auth0.jwt            | v3.3.0  |
| commons-fileupload   | v1.3.3  |
| mysql                | v5.7.29 |
| mysql-connector-java | v5.1.48 |

## 运行

1. 使用IDEA导入项目
2. 快捷键 `ctrl+shift+Alt+S` ， 选中 `project Setting` 中的  `Project` ，在右侧对话框中选择本地的JDK，大版本是1.8即可
3. 选中右侧边栏 `maven` ，选中弹窗中左上角图标 `Reimport All maven project`
4. 运行 `WebsiteBackApplication`

## 主要功能

1. 用户管理
2. 课程管理
3. 章节管理，无限层级（closure table）
4. 个人信息管理
5. 课程资源管理
6. 单点登陆

## 数据库

在存储章节目录结构时使用了闭包表的数据结构，实现无限层级的章节树。

关于闭包表详情请参考[KACIRAS的文章](https://blog.kaciras.com/article/6/store-tree-in-database)

## 已知存在问题

1. 章节无法自由调整顺序
## 未实现功能

1. 分页查询
2. 统一异常管理
3. 日志事务

