/* Populate the tables with our data */
insert into Companion_type values(111, 'Jaesa Willsaam',1,null);
insert into Class_companion_map values(103, 111);
-- opinion positive
insert into Companion_opinion values(111, 'Random cruelty', 1);
insert into Companion_opinion values(111, 'Secrets of the force', 1);
insert into Companion_opinion values(111, 'Murder', 1);
insert into Companion_opinion values(111, 'Chaos',1);
-- opinion negative
insert into Companion_opinion values(111, 'Honor', 0);
insert into Companion_opinion values(111, 'Mercy',0);
insert into Companion_opinion values(111, 'Helping',0);

-- high
insert into Companion_gift_prefs values(111,310,1);
-- medium
insert into Companion_gift_prefs values(111,308,2);
insert into Companion_gift_prefs values(111,304,2);
-- low
insert into Companion_gift_prefs values(111,303,3);
insert into Companion_gift_prefs values(111,305,3);

insert into Companion_crewskill_bonus values(111, '+5 Archaeology Efficiency', '+5 Synthweaving Critical');
insert into Companion_stats values(111, 'Willpower', 'Medium', 'Polesaber and focus');
insert into Companion_notes values(111, 'Skins sold at ilum security key vendor','Weapon gift depends on weapon',null);

