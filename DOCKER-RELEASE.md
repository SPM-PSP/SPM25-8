# 个人健康管理系统 Docker 部署指南

## 版本信息
- 版本: 1.0.0
- 发布日期: 2023-11-22

## 系统要求
- Docker 20.10+
- Docker Compose 2.0+
- 最小配置: 2核CPU, 4GB内存, 20GB存储空间

## 快速部署

### 1. 克隆仓库

```bash
git clone https://github.com/SPM-PSP/SPM25-8.git
cd SPM25-8/源代码
```

### 2. 一键启动

```bash
docker-compose up -d
```

### 3. 访问系统
- 前端界面: http://localhost:21091
- API接口: http://localhost:21090

## 默认账户
- 管理员: admin/admin123
- 测试用户: user/user123

## 数据持久化
系统数据存储在名为`mysql_data`的Docker卷中，确保数据安全和持久化。

## 常用命令

```bash
# 查看运行状态
docker-compose ps

# 查看日志
docker-compose logs -f

# 停止服务
docker-compose down

# 停止并删除数据卷(慎用!)
docker-compose down -v
```

## 配置说明
1. 数据库配置:
   - 在docker-compose.yml的环境变量中配置

2. 端口映射:
   - 前端: 21091 -> 80
   - 后端: 21090 -> 21090
   - MySQL: 3307 -> 3306

## 自定义配置
如需修改端口或其他配置，请编辑`docker-compose.yml`文件。

## 故障排查
1. 无法访问前端界面
   - 检查容器运行状态: `docker-compose ps`
   - 查看前端日志: `docker-compose logs frontend`

2. 后端API连接失败
   - 检查API容器状态: `docker-compose logs api`
   - 确认MySQL是否正常运行并初始化完成

3. 数据库连接失败
   - 检查MySQL容器日志: `docker-compose logs mysql`
