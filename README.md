# Product Viewer

Foobar is a Python library for dealing with word pluralization.

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