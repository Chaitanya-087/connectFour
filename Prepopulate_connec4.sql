use Connect4;


INSERT INTO player(player_name,player_id)
VALUES
('sai',001),
('sri',002);

INSERT INTO game(game_id,winner_id)
VALUES
(15,001),
(16,002);

INSERT INTO moves(game_id ,
	player_id ,
	col_number ,
	row_num ,
	move_number 
)
VALUES
(15,001,1,4,1),
(15,002,4,5,2),
(15,001,2,4,3),
(15,002,3,4,4);

SELECT * FROM player;
SELECT * FROM game;
SELECT *FROM moves;

