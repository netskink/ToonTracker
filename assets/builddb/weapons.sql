/* Populate the tables with our data */
insert into Weapon_type values(101, "Blaster Rifle");
insert into Weapon_type values(102, "Blaster Pistol");
insert into Weapon_type values(103, "Sniper Rifle");
insert into Weapon_type values(104, "Vibroknife");
insert into Weapon_type values(105, "Shield Generator");
insert into Weapon_type values(106, "Cannon");
insert into Weapon_type values(107, "Lightsaber");
insert into Weapon_type values(108, "Double-bladed Lightsaber");
insert into Weapon_type values(109, "Force Focus");
insert into Weapon_type values(110, "Power Generator");
insert into Weapon_type values(111, "Electrostaff");

insert into Weapon_slot values(201, "Main hand");
insert into Weapon_slot values(202, "Off hand");

-- here we create some sample weapons
-- a level 11 blaster pistol which can only be used in main hand, with augments but no sockets and boost aim with rating of 17
insert into Weapon values(2001, 102, 201, null, 11, 202, 0, 1,17);
-- a level 12 lightsaber which can be used in either hand, has no augments or mods and boost str with rating of 21
insert into Weapon values(2002, 107, 201, 202, 12, 201, 0, 0,21);



