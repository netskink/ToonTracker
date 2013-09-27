/* Populate the tables with our data */
insert into Companion_type values(102, 'Khem Val',1,null);
insert into Class_companion_map values(104, 102);
insert into Companion_opinion values(102, 'Killing Force Users',1);
insert into Companion_opinion values(102, 'Showing Strength',1);
insert into Companion_opinion values(102, 'Making fools look stupid',1);

insert into Companion_opinion values(102, 'Weakness', 0);
insert into Companion_opinion values(102, 'Not killing force users',0);

insert into Companion_gift_prefs values(102,302,1);

insert into Companion_gift_prefs values(102,310,2);

insert into Companion_gift_prefs values(102,303,3);
insert into Companion_gift_prefs values(102,307,3);
insert into Companion_gift_prefs values(102,308,3);

insert into Companion_crewskill_bonus values(102, '+15 Artifice', '+5 Investigation');
insert into Companion_stats values(102, 'Strength', 'Heavy', 'Vibrosword and Shield Generator');
insert into Companion_notes values(102, 'not sure',null,null);

