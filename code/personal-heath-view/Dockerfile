FROM nginx:alpine

# 拷贝构建好的 dist 文件到 nginx html 文件夹
COPY dist /usr/share/nginx/html
RUN rm -rf /etc/nginx/conf.d/default.conf
COPY default.conf /etc/nginx/conf.d/default.conf

# 启动 nginx
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
