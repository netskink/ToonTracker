/* Populate the tables with our data */
insert into Companion_type values(119, 'Broonmark',1,null);
insert into Class_companion_map values(103, 119);
-- opinion positive
insert into Companion_opinion values(119, 'Violence as a solution', 1);
insert into Companion_opinion values(119, 'Tests of courage', 1);
insert into Companion_opinion values(119, 'Teamwork', 1);
-- opinion negative
insert into Companion_opinion values(119, 'Betrayl of allies', 0);
insert into Companion_opinion values(119, 'Inaction',0);
insert into Companion_opinion values(119, 'Talking',0);

-- high
insert into Companion_gift_prefs values(119,310,1);
insert into Companion_gift_prefs values(119,308,1);
-- medium
insert into Companion_gift_prefs values(119,306,2);
insert into Companion_gift_prefs values(119,307,2);
-- low
insert into Companion_gift_prefs values(119,302,3);
insert into Companion_gift_prefs values(119,305,3);
insert into Companion_gift_prefs values(119,303,3);

insert into Companion_crewskill_bonus values(119, '+10 Scavenging Efficiency', '+2 Bioanalysis Critical');
insert into Companion_stats values(119, 'Strength', 'Heavy', 'Vibrosword and Shield Generator');
insert into Companion_notes values(119, 'Melee Tank','Hoth',null);

