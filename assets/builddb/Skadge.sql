/* Populate the tables with our data */
insert into Companion_type values(122, 'Skadge',1,null);
insert into Class_companion_map values(101, 122);
insert into Companion_opinion values(122, 'Violence', 1);
insert into Companion_opinion values(122, 'Meaness',1);
insert into Companion_opinion values(122, 'Destruction',1);
insert into Companion_opinion values(122, 'Bullying',1);

insert into Companion_opinion values(122, 'Compromise', 0);
insert into Companion_opinion values(122, 'Taking Orders',0);
insert into Companion_opinion values(122, 'Weakness',0);


insert into Companion_gift_prefs values(122,309,1);
insert into Companion_gift_prefs values(122,310,1);

insert into Companion_gift_prefs values(122,304,3);
insert into Companion_gift_prefs values(122,305,3);
insert into Companion_gift_prefs values(122,308,3);


insert into Companion_crewskill_bonus values(122, '+10 Scavenging Efficiency', '+2 Treasure Hunting critical');
insert into Companion_stats values(122, 'Aim', 'Heavy', 'Techblade with power/shield Generator');
insert into Companion_notes values(122, '','Belsavis','melee tank');

