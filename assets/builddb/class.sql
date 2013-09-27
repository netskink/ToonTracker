/* Populate the tables with our data */
insert into Role values(111, "Tank");
insert into Role values(222, "Melee DPS");
insert into Role values(333, "Ranged DPS");
insert into Role values(444, "Healer");

insert into Faction values(001, "Imperial");
insert into Faction values(002, "Republic");


-- Bounty Hunter - bodygaurd are aim/alacrity all others are aim/power?
--AdvClassspecial(ID, _advClassID, advClassSpecialName, roleID, primaryStatID, secondaryStatID); 
insert into Adv_class_special values(301, 201, "Shield Tech", 111,202,306);
insert into Adv_class_special values(302, 201, "Advanced Prototype",222,202,306);
insert into Adv_class_special values(303, 201, "Powertech Pyrotech", 222,202,306); -- common tree
insert into Adv_class_special values(304, 202, "Bodyguard", 444,202,303);
insert into Adv_class_special values(305, 202, "Arsenal",333,202,306);
insert into Adv_class_special values(306, 202, "Mercenary Pyrotech", 333,202,306); -- common tree
-- AdvClass(ID, _classID, advClassName);
insert into Adv_class values(201, 101, "Powertech",102,105,110,101); -- tank or short ranged dps, cannon is guess
insert into Adv_class values(202, 101, "Mercenary",102,102,null,101); -- long range dps or healing
-- Class(_classID, className, faction); 
insert into Class values(101, "Bounty Hunter", 001); 

-- Imperial Agent 
--AdvClassspecial(ID, _advClassID, advClassSpecialName, roleID, primaryStatID, secondaryStatID); 
-- operative cunning/critical sniper cunning/Accuracy
insert into Adv_class_special values(307, 203, "Medicine", 444,204,304);
insert into Adv_class_special values(308, 203, "Concealment",222,204,304);
insert into Adv_class_special values(309, 203, "Operative Lethality", 222,204,304); -- common tree
insert into Adv_class_special values(310, 204, "Marksman", 333,204,301);
insert into Adv_class_special values(311, 204, "Engineering",333,204,301);
insert into Adv_class_special values(312, 204, "Sniper Lethality", 333,204,301); -- common tree
-- AdvClass(ID, _classID, advClassName);
insert into Adv_class values(203, 102, "Operative", 101,104,null,102); -- healing or melee
insert into Adv_class values(204, 102, "Sniper", 103,104,null,102); -- long range dps only
-- Class(_classID, className, faction); 
insert into Class values(102, "Imperial Agent", 001); 


-- Sith Warrior
--AdvClassspecial(ID, _advClassID, advClassSpecialName, roleID, primaryStatID, secondaryStatID); 
-- Juggernaut Strength/defense Marauder strength/critical
insert into Adv_class_special values(313, 205, "Immortal", 111,201,305);
insert into Adv_class_special values(314, 205, "Vengance",222,201,305);
insert into Adv_class_special values(315, 205, "Juggernaut Rage", 222,201,305); -- common tree
insert into Adv_class_special values(316, 206, "Annihilation", 222,201,305);
insert into Adv_class_special values(317, 206, "Carnage",222,201,305);
insert into Adv_class_special values(318, 206, "Marauder Rage", 222,201,305); -- common tree
-- AdvClass(ID, _classID, advClassName, weapon1, weapon2);
insert into Adv_class values(205, 103, "Juggernaut", 107,104,109,101); -- tank or melee
insert into Adv_class values(206, 103, "Marauder", 107,107,null,102); -- melee dps only
-- Class(_classID, className, faction); 
insert into Class values(103, "Sith Warrior", 001); 


-- Sith Inquisitor
--AdvClassspecial(ID, _advClassID, advClassSpecialName, roleID, primaryStatID, secondaryStatID); 
-- Inquisitor willpower/crit
insert into Adv_class_special values(319, 207, "Darkness", 111,205,304);
insert into Adv_class_special values(320, 207, "Deception",222,205,304); -- pvp
insert into Adv_class_special values(321, 207, "Assassin Madness", 222,205,304); -- common tree
insert into Adv_class_special values(322, 208, "Corruption", 444,205,304);
insert into Adv_class_special values(323, 208, "Lightning",333,205,304);
insert into Adv_class_special values(324, 208, "Sorcerer Madness", 333,205,304); -- common tree
-- AdvClass(ID, _classID, advClassName, weapon1, weapon2, weapon2, armor);
insert into Adv_class values(207, 104, "Assassin", 108,111,105,103); -- tank or melee
insert into Adv_class values(208, 104, "Sorcerer", 107,109,null,103); -- heals or ranged dps
-- Class(_classID, className, faction); 
insert into Class values(104, "Sith Inquisitor", 001); 

-----------------------------------------------------------------------
-----------------------------------------------------------------------


-- Jedi Counsular
--AdvClassspecial(ID, _advClassID, advClassSpecialName, roleID, primaryStatID, secondaryStatID); 
-- Counsular willpower/crit
insert into Adv_class_special values(325, 209, "Kinetic Combat", 111,205,304);
insert into Adv_class_special values(326, 209, "Infiltration",222,205,304); -- pvp
insert into Adv_class_special values(327, 209, "Shadow Balance", 222,205,304); -- common tree
insert into Adv_class_special values(328, 210, "Seer", 444,205,304);
insert into Adv_class_special values(329, 210, "Telekinetics",333,205,304);
insert into Adv_class_special values(330, 210, "Sage Balance", 333,205,304); -- common tree
-- AdvClass(ID, _classID, advClassName, weapon1, weapon2, weapon2, armor);
insert into Adv_class values(209, 105, "Shadow", 108,111,105,103); -- tank or melee
insert into Adv_class values(210, 105, "Sage", 107,109,null,103); -- heals or ranged dps
-- Class(_classID, className, faction); 
insert into Class values(105, "Jedi Counsular", 002); 


-- Trooper- combat medic are aim/alacrity all others are aim/power?
--AdvClassspecial(ID, _advClassID, advClassSpecialName, roleID, primaryStatID, secondaryStatID); 
insert into Adv_class_special values(331, 211, "Shield Specialist", 111,202,306);
insert into Adv_class_special values(332, 211, "Tactics",222,202,306);
insert into Adv_class_special values(333, 212, "Vanguard Assault Specialist", 333,202,306); -- common tree
insert into Adv_class_special values(334, 212, "Combat Medic", 444,202,303);
insert into Adv_class_special values(335, 212, "Gunnery",333,202,306);
insert into Adv_class_special values(336, 212, "Commando Assault Specialist", 333,202,306); -- common tree
-- AdvClass(ID, _classID, advClassName);
insert into Adv_class values(211, 106, "Vanguard",102,105,110,101); -- tank or short ranged dps
insert into Adv_class values(212, 106, "Commando",102,102,null,101); -- long range dps or healing
-- Class(_classID, className, faction); 
insert into Class values(106, "Trooper", 002); 



