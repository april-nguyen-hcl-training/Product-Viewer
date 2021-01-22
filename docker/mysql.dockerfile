FROM mariadb:latest
ENV MYSQL_ROOT_PASSWORD: root
ENV MYSQL_USER: root
ENV MYSQL_PASSWORD: root
ENV MYSQL_DATABASE: ecommerce
COPY *.sql /docker-entrypoint-initdb.d/