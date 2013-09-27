/* Populate the tables with our data */
insert into Companion_type values(109, 'Gault',1,null);
insert into Class_companion_map values(101, 109);
-- opinion positive
insert into Companion_opinion values(109, 'Greed', 1);
insert into Companion_opinion values(109, 'Thinking your way through a problem', 1);
insert into Companion_opinion values(109, 'Indulgence', 1);
-- opinion negative
insert into Companion_opinion values(109, 'Fair Fights', 0);
insert into Companion_opinion values(109, 'Pain',0);
insert into Companion_opinion values(109, 'Charity',0);
insert into Companion_opinion values(109, 'Rules',0);

-- high
insert into Companion_gift_prefs values(109,304,1);
-- medium
insert into Companion_gift_prefs values(109,309,2);
-- low
-- I am not certain these gifts are accurate
insert into Companion_gift_prefs values(109,302,3);
insert into Companion_gift_prefs values(109,307,3);
insert into Companion_gift_prefs values(109,310,3);

insert into Companion_crewskill_bonus values(109, '+10 Underworld Trading', '+2 Biochem Critical');
insert into Companion_stats values(109, 'Cunning', 'Medium', 'sniper rifle with Vibroknife');
insert into Companion_notes values(109, 'Ranged DPS','non-romance',null);

