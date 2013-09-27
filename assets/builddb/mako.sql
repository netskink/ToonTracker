/* Populate the tables with our data */
insert into Companion_type values(104, 'Mako',1,null);
insert into Class_companion_map values(101, 104);
-- positive opinion
insert into Companion_opinion values(104, 'Light Users',1);
insert into Companion_opinion values(104, 'Money', 1);
insert into Companion_opinion values(104, 'Freedom',1);
insert into Companion_opinion values(104, 'Kindness/joking',1);
-- negative opinion
insert into Companion_opinion values(104, 'Loyalty to empire',0);
insert into Companion_opinion values(104, 'Cruelty',0);
insert into Companion_opinion values(104, 'Bullying',0);
insert into Companion_opinion values(104, 'Snobs',0);
-- high
insert into Companion_gift_prefs values(104,301,1);
insert into Companion_gift_prefs values(104,307,1);
-- medium
insert into Companion_gift_prefs values(104,309,2);
-- low
insert into Companion_gift_prefs values(104,304,3);
insert into Companion_gift_prefs values(104,302,3);
insert into Companion_gift_prefs values(104,308,3);
insert into Companion_gift_prefs values(104,310,3);

insert into Companion_crewskill_bonus values(104, '+15 Slicing Efficiency', '+5 Cybertech efficency');
insert into Companion_stats values(104, 'Cunning and endurance', 'Medium', 'Blaster pistol with Vibroknife');
insert into Companion_notes values(104, 'Can romance with male',null,null);

