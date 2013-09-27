/* Populate the tables with our data */
insert into Companion_type values(110, 'Vector Hyllus',1,null);
insert into Class_companion_map values(102, 110);
-- opinion positive
insert into Companion_opinion values(110, 'Diplomacy', 1);
insert into Companion_opinion values(110, 'Helping others', 1);
insert into Companion_opinion values(110, 'Exploring Alien Cultures', 1);
-- opinion negative
insert into Companion_opinion values(110, 'Greed', 0);
insert into Companion_opinion values(110, 'Cruelty',0);
insert into Companion_opinion values(110, 'Prejudice behavior',0);
insert into Companion_opinion values(110, 'Alien Racisim',0);

-- high
insert into Companion_gift_prefs values(110,302,1);
-- medium
insert into Companion_gift_prefs values(110,301,2);
insert into Companion_gift_prefs values(110,303,2);
-- low
-- I am not certain these gifts are accurate
insert into Companion_gift_prefs values(110,310,3);
insert into Companion_gift_prefs values(110,304,3);
insert into Companion_gift_prefs values(110,308,3);

insert into Companion_crewskill_bonus values(110, '+5 Bioanalysis efficiency', '+5 Diplomacy crticial');
insert into Companion_stats values(110, 'Willpower', 'Light', 'Electrostaff with Generator (non-shield)');
insert into Companion_notes values(110, 'Melee Damage','can romance with female','best to use with light side toon');

