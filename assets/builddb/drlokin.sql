/* Populate the tables with our data */
insert into Companion_type values(113, 'Dr Lokin',1,null);
insert into Class_companion_map values(102, 113);
insert into Companion_opinion values(113, 'Clever solutions', 1);
insert into Companion_opinion values(113, 'Long-term thinking',1);
insert into Companion_opinion values(113, 'Technology',1);
insert into Companion_opinion values(113, 'Pragmatism',1);

insert into Companion_opinion values(113, 'Ideaology', 0);
insert into Companion_opinion values(113, 'Honesty',0);
insert into Companion_opinion values(113, 'Selfish actions without clear long-term gain',0);


insert into Companion_gift_prefs values(113,307,1);

insert into Companion_gift_prefs values(113,305,2);
insert into Companion_gift_prefs values(113,304,2);

insert into Companion_gift_prefs values(113,303,3);
insert into Companion_gift_prefs values(113,306,3);
insert into Companion_gift_prefs values(113,309,3);


insert into Companion_crewskill_bonus values(113, '+15 Biochem Efficiency', '+10 Investigation Efficiency');
insert into Companion_stats values(113, 'Cunning', 'Medium', 'Blaster pistol with Vibroknife');
insert into Companion_notes values(113, 'non-romanceable',null,null);

