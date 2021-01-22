# Product Viewer

Product Viewer is a Java project using Maven, Apache Tomcat, and a MySQL database.

## Installation

Use [docker](https://docs.docker.com/get-docker/) to install.

```bash
docker-compose --file docker/compose.yaml up --build -d
```

## Usage

In browser, go to [link](http://localhost:8081/product/).

## Stopping and Removing

```bash
docker-compose --file docker/compose.yaml down
docker image rm db webapp mariadb maven tomcat
```

## License
[MIT](https://choosealicense.com/licenses/mit/)