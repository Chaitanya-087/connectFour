use Connect4;


CREATE TABLE player(
	player_name VARCHAR(200) NOT NULL,
	player_id INT PRIMARY KEY
);

CREATE TABLE game(
	game_id INT PRIMARY KEY,
	winner_id int ,
	FOREIGN KEY (winner_id) REFERENCES player(player_id)
);

CREATE TABLE moves(
	game_id INT,
	player_id INT,
	col_number INT NOT NULL,
	row_num INT NOT NULL,
	move_number INT PRIMARY KEY,
	FOREIGN KEY (game_id) REFERENCES game(game_id),
	FOREIGN KEY (player_id) REFERENCES player(player_id)
);
