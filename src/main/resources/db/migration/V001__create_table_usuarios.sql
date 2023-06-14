CREATE TABLE usuarios(
  id BIGINT(20) AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  hierarquia BIGINT(20),
  forca_senha varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;