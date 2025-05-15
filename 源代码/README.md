# 个人健康项目 Docker 部署说明

本项目为前后端分离架构，使用 Docker 和 Docker Compose 进行容器化部署。

## 项目架构
- 前端: Vue.js (端口: 21091)
- 后端: Spring Boot (端口: 21090)
- 数据库: MySQL 8.0

## 部署步骤

### 1. 安装必要软件

确保你的服务器上已安装:
- Docker (最新版)
- Docker Compose (最新版)

### 2. 克隆代码库

```bash
git clone <项目仓库URL>
cd <项目目录>
```

### 3. 启动项目

在项目根目录运行:

```bash
# 构建并启动所有服务
docker-compose up -d

# 查看日志
docker-compose logs -f
```

### 4. 访问应用

- 前端应用: http://localhost:21091
- 后端API: http://localhost:21090

### 5. 停止项目

```bash
docker-compose down
```

如需完全删除所有数据(包括数据库数据):

```bash
docker-compose down -v
```

## 配置说明

- 数据库连接: MySQL数据库在启动时会自动执行`sql`目录中的SQL脚本
- 默认用户名/密码: root/root123

## 注意事项

- 首次启动可能需要几分钟时间，请耐心等待
- 如需修改端口或其他配置，请编辑`docker-compose.yml`文件 