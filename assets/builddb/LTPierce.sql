/* Populate the tables with our data */
insert into Companion_type values(114, 'Lt Pierce',1,null);
insert into Class_companion_map values(103, 114);
-- opinion positive
insert into Companion_opinion values(114, 'Personal gain', 1);
insert into Companion_opinion values(114, 'Hurting the republic', 1);
insert into Companion_opinion values(114, 'Danger', 1);
insert into Companion_opinion values(114, 'Laughing at authority',1);
-- opinion negative
insert into Companion_opinion values(114, 'Rules', 0);
insert into Companion_opinion values(114, 'Kissing up',0);
insert into Companion_opinion values(114, 'Peace',0);

-- high
insert into Companion_gift_prefs values(114,310,1);
insert into Companion_gift_prefs values(114,305,1);
-- medium
-- low
insert into Companion_gift_prefs values(114,307,3);
insert into Companion_gift_prefs values(114,303,3);
insert into Companion_gift_prefs values(114,306,3);
insert into Companion_gift_prefs values(114,308,3);
insert into Companion_gift_prefs values(114,309,3);

insert into Companion_crewskill_bonus values(114, '+5 Archaeology Efficiency', '+5 Synthweaving Critical');
insert into Companion_stats values(114, 'Aim', 'Heavy', 'Blaster rifle/pistol and Shield Generator');
insert into Companion_notes values(114, 'Ranged Tank','Taris',null);

