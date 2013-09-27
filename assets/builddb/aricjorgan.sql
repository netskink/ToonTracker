/* Populate the tables with our data */
insert into Companion_type values(105, 'Aric Jorgan',2,null);
insert into Class_companion_map values(106, 105);
insert into Companion_opinion values(105, 'Efficiency',1);
insert into Companion_opinion values(105, 'Duty',1);
insert into Companion_opinion values(105, 'Honesty',1);
insert into Companion_opinion values(105, 'Loyalty to Republic',1);
insert into Companion_opinion values(105, 'Excuses',0);
insert into Companion_opinion values(105, 'Killing innocents',0);
insert into Companion_opinion values(105, 'Failure',0);

-- high
insert into Companion_gift_prefs values(105,310,1);
-- medium
insert into Companion_gift_prefs values(105,301,2);
insert into Companion_gift_prefs values(105,305,2);
-- low
insert into Companion_gift_prefs values(105,302,3);
insert into Companion_gift_prefs values(105,306,3);
insert into Companion_gift_prefs values(105,307,3);
insert into Companion_gift_prefs values(105,308,3);


insert into Companion_crewskill_bonus values(105, '+10 Armstech efficiency', '+2 Diplomancy Critical');
insert into Companion_stats values(105, 'Aim', 'Heavy', 'sniper/blaster rifle or assault cannon with non-shield power generator');
insert into Companion_notes values(105, 'Romancable',null,null);

