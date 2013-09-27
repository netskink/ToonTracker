/* Populate the tables with our data */
insert into Companion_type values(108, 'Andronikos Revel',1,null);
insert into Class_companion_map values(104, 108);
-- opinion positive
insert into Companion_opinion values(108, 'Action', 1);
insert into Companion_opinion values(108, 'Keeping Promises', 1);
insert into Companion_opinion values(108, 'Complications', 1);
-- opinion negative
insert into Companion_opinion values(108, 'Authority', 0);
insert into Companion_opinion values(108, 'Betrayal',0);
insert into Companion_opinion values(108, 'Backing down',0);

-- high
insert into Companion_gift_prefs values(108,310,1);
-- medium
insert into Companion_gift_prefs values(108,305,2);
insert into Companion_gift_prefs values(108,309,2);
-- low
-- I am not certain these gifts are accurate
insert into Companion_gift_prefs values(108,301,3);
insert into Companion_gift_prefs values(108,304,3);
insert into Companion_gift_prefs values(108,308,3);

insert into Companion_crewskill_bonus values(108, '+2 Underworld Trading critical', '+2 Slicing critical');
insert into Companion_stats values(108, 'Cunning', 'Medium', 'Duel Weld Blaster pistol');
insert into Companion_notes values(108, 'Ranged DPS','Can romance with female','gift prefs are for female toon');

