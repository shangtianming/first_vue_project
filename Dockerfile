# 设置基础镜像
FROM nginx

# 定义作者
MAINTAINER WhiteWall

# 删除目录下的default.conf文件 , 原本就没有这个文件
RUN rm /etc/nginx/conf.d/default.conf

# 将当前目录下的default.conf添加到nginx目录下
ADD default.conf /etc/nginx/conf.d/

# 将dist文件中的内容复制到 /usr/share/nginx/html/ 这个目录下面
COPY dist/  /usr/share/nginx/html/