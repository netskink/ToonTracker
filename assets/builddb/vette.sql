/* Populate the tables with our data */
insert into Companion_type values(101, 'Vette',1,null);
insert into Class_companion_map values(103, 101);
-- opinion positive
insert into Companion_opinion values(101, 'Anti-authority', 1);
insert into Companion_opinion values(101, 'Protecting the weak', 1);
insert into Companion_opinion values(101, 'Treasure', 1);
insert into Companion_opinion values(101, 'Getting paid',1);
-- opinion negative
insert into Companion_opinion values(101, 'Bullying', 0);
insert into Companion_opinion values(101, 'Killing innocents',0);
insert into Companion_opinion values(101, 'Kissing up',0);

-- high
insert into Companion_gift_prefs values(101,309,1);
-- medium
insert into Companion_gift_prefs values(101,302,2);
insert into Companion_gift_prefs values(101,304,2);
-- low
insert into Companion_gift_prefs values(101,301,3);
insert into Companion_gift_prefs values(101,303,3);
insert into Companion_gift_prefs values(101,306,3);
insert into Companion_gift_prefs values(101,307,3);
insert into Companion_gift_prefs values(101,310,3);

insert into Companion_crewskill_bonus values(101, '+5 Underworld trading efficiency', '+5 Treasure Hunting Critical');
insert into Companion_stats values(101, 'Cunning', 'Medium', 'blaster rifle or pistols');
insert into Companion_notes values(101, 'Cunning adds to bombs dmg, but aim does not.','Weapon gift depends on weapon',null);

