/* Populate the tables with our data */
insert into Companion_type values(112, 'Ashara Zavros',1,null);
insert into Class_companion_map values(104, 112);
insert into Companion_opinion values(112, 'Rational choices',1);
insert into Companion_opinion values(112, 'Secrets of the force',1);
insert into Companion_opinion values(112, 'Fighting bullies',1);

insert into Companion_opinion values(112, 'Random cruelty', 0);
insert into Companion_opinion values(112, 'Fighting jedi',0);

insert into Companion_gift_prefs values(112,305,1);
insert into Companion_gift_prefs values(112,310,1);

insert into Companion_gift_prefs values(112,301,2);
insert into Companion_gift_prefs values(112,306,2);

insert into Companion_gift_prefs values(112,302,3);
insert into Companion_gift_prefs values(112,304,3);
insert into Companion_gift_prefs values(112,309,3);

insert into Companion_crewskill_bonus values(112, '+10 Synthweaving eff', '+10 Diplomacy');
insert into Companion_stats values(112, 'Strength', 'Medium', 'two light sabers');
insert into Companion_notes values(112, 'melee dps','c artifacts, mil gear,rep mem,weapon all 1 star','torhead female gifts');

