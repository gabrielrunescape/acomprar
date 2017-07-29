-- CURRENT VERSION APPLICATION A0 --
CREATE TABLE IF NOT EXISTS `Categoria` (
	ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	Nome VARCHAR(32) NOT NULL,
	Tipo CHAR NOT NULL CHECK (Tipo = '+' OR Tipo = '-')
);

CREATE TABLE IF NOT EXISTS `Rendimento` (
	ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	Valor DOUBLE NOT NULL,
	dt_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	dt_vencimento DATETIME NOT NULL,
	Categoria INTEGER NOT NULL,
	CONSTRAINT fk_Rendimento_Categoria FOREIGN KEY (Categoria) REFERENCES Categoria(ID)
);