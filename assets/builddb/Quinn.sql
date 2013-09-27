/* Populate the tables with our data */
insert into Companion_type values(107, 'Malavai Quinn',1,null);
insert into Class_companion_map values(103, 107);
-- opinion positive
insert into Companion_opinion values(107, 'Patriotism to the empire', 1);
insert into Companion_opinion values(107, 'Rewarding hard work', 1);
insert into Companion_opinion values(107, 'Honor', 1);
-- opinion negative
insert into Companion_opinion values(107, 'Selfishness', 0);
insert into Companion_opinion values(107, 'Betrayal',0);
insert into Companion_opinion values(107, 'Irrational behavior',0);

-- high
insert into Companion_gift_prefs values(107,303,1);
insert into Companion_gift_prefs values(107,305,1);
insert into Companion_gift_prefs values(107,310,1);
-- medium
insert into Companion_gift_prefs values(107,307,2);
insert into Companion_gift_prefs values(107,308,2);
-- low
-- I am not certain these gifts are accurate
insert into Companion_gift_prefs values(107,301,3);

insert into Companion_crewskill_bonus values(107, '+10 Armstech efficiency', '+10 Diplomacy efficiency');
insert into Companion_stats values(107, 'Cunning', 'Medium', 'blaster pistol with Vibroknife');
insert into Companion_notes values(107, 'Healer','can romance with female',null);

