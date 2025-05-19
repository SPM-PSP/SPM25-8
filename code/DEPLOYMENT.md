## 部署指南

> 本文档适用于 **Personal-Health** 前后端分离项目的本地或服务器部署。项目采用 `Spring Boot + MyBatis + MySQL` 后端，`Vue 2.x` 前端，并提供官方 **Docker** 镜像与 `docker-compose` 一键运行方式。

---

### 一、准备工作

1. **系统**：推荐 Linux (ubuntu 20.04+/CentOS 7+)；Windows/Mac 亦可。
2. **运行环境**：
   * JDK 8（后端编译/运行需要）
   * Maven 3.6+（若不使用 Docker）
   * Node.js 14。16.0+/npm 6+（前端构建需要，若不使用 Docker）
   * MySQL 5.7/8.0（已在脚本中使用 `utf8mb4` 字符集）
   * Docker 20.10+ 与 Docker Compose 1.29+（推荐使用官方镜像部署）
3. **端口占用**：
   * 后端默认 `21090`
   * 前端 (Nginx) 默认 `80`
   * MySQL 默认 `3306`

---

### 二、数据库初始化

```bash
# 登录本地或远程 MySQL
mysql -u root -p

# 创建数据库（若脚本中已包含 CREATE DATABASE 可跳过）
CREATE DATABASE personal_health DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

然后导入脚本：

```bash
mysql -u root -p personal_health < sql/personal_health.sql
```

> 如需自定义数据库名/密码，请在稍后修改后端 `application.yml` 中的 `spring.datasource.*`。

---

### 三、后端部署 (Spring Boot)

#### 3.1 本地运行（非 Docker）

```bash
cd personal-health-api

# 修改数据库连接
vim src/main/resources/application.yml   # 或其他编辑器
# spring.datasource.url、username、password

# 构建
mvn clean package -DskipTests

# 运行（上传目录可自行调整）
java -Dcustom.upload.dir=/app -jar target/personal-health-api-1.0-SNAPSHOT.jar
```

后台进程请使用 `&`, `nohup`, `systemd` 或 PM2 等工具托管。

#### 3.2 Docker 方式

```bash
cd personal-health-api

# 可在构建前修改 application.yml 或通过挂载覆盖
# 构建镜像
docker build -t personal-health-api:1.0 .

# 运行容器（示例：绑定 21090 端口、挂载上传目录）
docker run -d \
  --name personal-health-api \
  -p 21090:21090 \
  -v /opt/personal-health/uploads:/app/pic \
  personal-health-api:1.0
```

---

### 四、前端部署 (Vue 2.x)

#### 4.1 本地构建/运行

```bash
cd personal-health-view

# 安装依赖（使用国内源可加速）
npm install --registry=https://registry.npmmirror.com

# 开发模式
npm run dev       # 默认 http://localhost:8080/

# 生产构建
npm run build     # 生成 dist/ 目录
```

将 `dist` 目录托管至任意 HTTP 服务 (Nginx/Apache)。 注意修改 `dist/default.conf` 中的 `proxy_pass` 地址，以指向后端服务，例如：

```nginx
location /api/ {
    proxy_pass http://<BACKEND_HOST>:21090;
    rewrite ^/api/?(.*)$ /$1 break;
}
```

#### 4.2 Docker 方式

```bash
cd personal-health-view

# 如需修改反向代理地址，先编辑 default.conf 再构建

docker build -t personal-health-web:1.0 .

docker run -d \
  --name personal-health-web \
  -p 80:80 \
  personal-health-web:1.0
```

---

### 五、docker-compose 一键启动（可选）

在项目根目录新建 `docker-compose.yml`，示例如下：

```yaml
version: "3.8"
services:
  db:
    image: mysql:5.7
    container_name: personal-health-db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: "root"
      MYSQL_DATABASE: personal_health
      TZ: Asia/Shanghai
    ports:
      - "3306:3306"
    volumes:
      - ./sql/personal_health.sql:/docker-entrypoint-initdb.d/01_init.sql
      - db_data:/var/lib/mysql

  backend:
    build: ./personal-health-api
    container_name: personal-health-api
    restart: always
    depends_on:
      - db
    ports:
      - "21090:21090"
    environment:
      JAVA_OPTS: "-Dcustom.upload.dir=/app"
    volumes:
      - uploads:/app/pic

  frontend:
    build: ./personal-health-view
    container_name: personal-health-web
    restart: always
    depends_on:
      - backend
    ports:
      - "80:80"

volumes:
  db_data:
  uploads:
```

一键启动：

```bash
docker compose up -d   # Docker Desktop 或 compose v2
```

---

### 六、常见问题

1. **端口被占用**：修改 `application.yml` 或 Docker `-p` 参数映射。
2. **数据库连接失败**：检查 MySQL 账号/密码、`application.yml` 地址，或 docker-compose 中网络配置。
3. **上传文件找不到**：确认 `-Dcustom.upload.dir` 与宿主机挂载目录一致，且权限正确。
4. **前端接口 404**：确认 Nginx `proxy_pass` 指向正确的后端地址，且前后端 `context-path` `/api/personal-heath/v1.0` 与反向代理的 rewrite 规则匹配。

---

### 七、目录结构速览

```text
projectManage/code/
│
├─ personal-health-api/   # Spring Boot 后端
│  ├─ Dockerfile
│  ├─ pom.xml
│  └─ src/
│
├─ personal-health-view/  # Vue 前端
│  ├─ Dockerfile
│  ├─ default.conf        # Nginx 反向代理配置
│  └─ src/
│
└─ sql/
   └─ personal_health.sql  # 数据库初始化脚本
```

至此，项目部署完成，浏览器访问 `http://<服务器IP>/` 即可进入系统首页。 
