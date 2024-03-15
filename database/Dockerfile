# Use the official MySQL image as a base
FROM mysql:latest

# Set environment variables for MySQL configuration
# These will be overridden by command line arguments or environment variables
ARG MYSQL_ROOT_PASSWORD=rootPassword
ARG MYSQL_DATABASE=BooKACab
ARG MYSQL_USER=bookacab
ARG MYSQL_PASSWORD=bookacab

# Set the environment variables in the container
ENV MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
ENV MYSQL_DATABASE=${MYSQL_DATABASE}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}

# Copy the schema.sql file into the container
COPY ./scripts/schema.sql /docker-entrypoint-initdb.d/schema.sql
