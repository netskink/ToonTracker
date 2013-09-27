/* Populate the tables with our data */
insert into Armor_type values(101, "Heavy");
insert into Armor_type values(102, "Medium");
insert into Armor_type values(103, "Light");
insert into Armor_type values(104, "Implant");
insert into Armor_type values(105, "Relic");
insert into Armor_type values(106, "Earpiece");

insert into Armor_slot values(601,"Head");
insert into Armor_slot values(602,"Chest");
insert into Armor_slot values(603,"Wrists");
insert into Armor_slot values(604,"Hands");
insert into Armor_slot values(605,"Waist");
insert into Armor_slot values(606,"Legs");
insert into Armor_slot values(607,"Feet");
insert into Armor_slot values(608,"Ears");
insert into Armor_slot values(609,"Implant 1");
insert into Armor_slot values(610,"Implant 2");


-- Here we will create a sample armor
-- lvl 11, heavy chest with sockets but no augments grats bonus to str with rating of 33.
insert into Armor values(1001,101,602,11,201,1,0, 33);

