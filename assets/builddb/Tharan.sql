/* Populate the tables with our data */
insert into Companion_type values(124, 'Tharan Cedrax',2,null);
insert into Class_companion_map values(105, 124);
-- opinion positive
insert into Companion_opinion values(124, 'Cleverness', 1);
insert into Companion_opinion values(124, 'Logical thinking', 1);
insert into Companion_opinion values(124, 'Aiding scientist and women', 1);
insert into Companion_opinion values(124, 'Getting something for nothing', 1);
-- opinion negative
insert into Companion_opinion values(124, 'Jedi', 0);
insert into Companion_opinion values(124, 'Force Persuade',0);
insert into Companion_opinion values(124, 'Destroying science',0);
insert into Companion_opinion values(124, 'Heroism that involves danger',0);

-- high
insert into Companion_gift_prefs values(124,304,1);
-- medium
insert into Companion_gift_prefs values(124,307,2);
-- low
insert into Companion_gift_prefs values(124,301,3);
insert into Companion_gift_prefs values(124,302,3);
insert into Companion_gift_prefs values(124,309,3);

insert into Companion_crewskill_bonus values(124, '+10 Cybertech Efficiency', '+10 Cybertech Efficiency');
insert into Companion_stats values(124, 'Cunning', 'Medium', 'Blaster Pistol and Scattergun');
insert into Companion_notes values(124, 'Jedi Consular','Romancable','Nar Shadda');

