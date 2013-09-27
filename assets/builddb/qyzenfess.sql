/* Populate the tables with our data */
insert into Companion_type values(106, 'Qyzen-Fess',2,null);
insert into Class_companion_map values(105, 106);
-- opinion positive
insert into Companion_opinion values(106, 'Killing powerful enemies', 1);
insert into Companion_opinion values(106, 'Encouraging others to defend themselves', 1);
insert into Companion_opinion values(106, 'Danger', 1);
insert into Companion_opinion values(106, 'Honor', 1);
-- opinion negative
insert into Companion_opinion values(106, 'Mercenary work', 0);
insert into Companion_opinion values(106, 'Killing weak',0);
insert into Companion_opinion values(106, 'Sparing powerful enemies',0);

-- high
insert into Companion_gift_prefs values(106,null,1);
-- medium
insert into Companion_gift_prefs values(106,310,2);
-- low
insert into Companion_gift_prefs values(106,305,3);
insert into Companion_gift_prefs values(106,307,3);
insert into Companion_gift_prefs values(106,309,3);

insert into Companion_crewskill_bonus values(106, '+15 Archaeology', '+5 Biochem or analysis?');
insert into Companion_stats values(106, 'Aim', 'Heavy', 'Vibrosword/techblade? with shield generator');
insert into Companion_notes values(106, 'Jedi Consular',null,null);

