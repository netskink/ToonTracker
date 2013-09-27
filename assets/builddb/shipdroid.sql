/* Populate the tables with our data */
insert into Companion_type values(123, 'Ship Droid',1,null);
insert into Class_companion_map values(101, 123);
-- opinion positive
insert into Companion_opinion values(123, null, 1);
-- opinion negative
insert into Companion_opinion values(123, null,0);

-- high
insert into Companion_gift_prefs values(123,302,1);
-- medium
insert into Companion_gift_prefs values(123,307,2);
-- low
-- I am not certain these gifts are accurate
insert into Companion_gift_prefs values(123,304,3);
insert into Companion_gift_prefs values(123,303,3);
insert into Companion_gift_prefs values(123,306,3);

insert into Companion_crewskill_bonus values(123, 'by droid part', 'by droid part');
insert into Companion_stats values(123, 'Aim', 'Droid', 'Generator');
insert into Companion_notes values(123, 'Heals','non-romance','Memobilia gifts are by faction');

