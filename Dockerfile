# Use the official MySQL 8.0 image
FROM mysql:8.0
  
  # Set root password and create a database
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=event
ENV MYSQL_USER=root
ENV MYSQL_TCP_PORT=3309
  
  # Use the native password plugin for better compatibility
CMD ["mysqld", "--default-authentication-plugin=mysql_native_password", "--port=3309"]
  
  # Expose MySQL port
EXPOSE 3309
  
  # Persist data in a volume addd comment----------
VOLUME /var/lib/mysql