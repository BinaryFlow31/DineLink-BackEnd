-- Drop user first if they exist
DROP USER if exists 'dinelinkadm'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'dinelinkadm'@'localhost' IDENTIFIED BY 'dinelinkpwd';

GRANT ALL PRIVILEGES ON * . * TO 'dinelinkadm'@'localhost';