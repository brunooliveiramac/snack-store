CREATE TABLE dbo.ingredient (
  id_ingredient VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  value money,
  PRIMARY KEY (id_ingredient)
);


CREATE TABLE dbo.snack (
  id_snack VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  total money,
  PRIMARY KEY (id_snack)
);


CREATE TABLE dbo.ingredient_item (
  id_ingredient_item VARCHAR(255) NOT NULL,
  quantity INT,
  value money,
  id_ingredient VARCHAR(255) NOT NULL,
  id_snack VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_ingredient_item),
  FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient),
  FOREIGN KEY (id_snack) REFERENCES snack(id_snack)
);
