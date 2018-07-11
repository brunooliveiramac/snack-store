INSERT into dbo.ingredient(id_ingredient, name, value) VALUES ('10', 'Lettuce', 0.40 ), ('11', 'Bacon', 2.00 ), ('12', 'Hamburguer', 3.00 ), ('13', 'Egg', 0.80 ), ('14', 'Cheese', 1.50 );

INSERT into dbo.snack(id_snack, name, total) VALUES ('10', 'X-Bacon', 6.50),('11', 'X-Burger', 4.50),('12', 'X-Egg', 5.30 ),('13', 'X-Egg Bacon', 7.3);

INSERT into dbo.ingredient_item(id_ingredient_item, quantity, value, id_ingredient, id_snack ) VALUES
('10', 1, 2.0, '11', '10'),('11', 1, 3.0, '12', '10'), ('12', 1, 1.50, '14', '10'),
('13', 1, 3.0, '12', '11'),('14', 1, 1.50, '14', '11'),
('15', 1, 0.8, '13', '12'),('16', 1, 3.00, '12', '12'),('17', 1, 1.50, '14', '12'),
('18', 1, 0.8, '13', '13'),('19', 1, 3.00, '12', '13'),('20', 1, 2.00, '11', '13'),('21', 1, 1.50, '14', '13');
