
-- the classes
drop table if exists Faction;
drop table if exists Role;
drop table if exists Adv_class_special;
drop table if exists Adv_class;
drop table if exists Class;

-- the crew skills
drop table if exists Crew_skill;
drop table if exists Crew_skill_types;
drop table if exists Crew_skill_yields;
drop table if exists  Crew_skill_needs;

-- the toon
drop table if exists Toon;
drop table if exists Toon_crewskill_levels;

-- the stats
drop table if exists Primary_stat;
drop table if exists Secondary_stat;

-- the armors
drop table if exists Armor_type;
drop table if exists Armor;
drop table if exists Armor_slot;

-- the weapons
drop table if exists Weapon_type;
drop table if exists Weapon;
drop table if exists Weapon_slot;

-- Gifts
drop table if exists Gifts;
drop table if exists Companion_gift_prefs;

-- Companions
drop table if exists Companion_type;
drop table if exists Companion_opinion;
drop table if exists Companion_crewskill;
drop table if exists Companion_crewskill_bonus;
drop table if exists Companion_stats;
drop table if exists Companion_notes;
drop table if exists Class_companion_map;

-- todo list
drop table if exists Todo_list;
drop table if exists Todo_list_items;

-- android tweaks
drop table if exists android_metadata;



-- An instance of a player character.
create table Faction(_factionID integer primary key, factionName text);
create table Toon(_toonID integer primary key, _advClassSpecialID integer, toonName text, _factionID integer, crewSkill1 integer, crewSkill2 integer, crewSkill3 integer);
create table Toon_crewskill_levels(_toonID integer, _crewSkillID integer, level integer);

create table Role(_roleID integer primary key, roleName text);
-- The player character class hierarchy
create table Adv_class_special(_advClassSpecialID integer primary key, _advClassID integer, advClassSpecialName text, roleID integer, primaryStatID integer, secondaryStatID integer);
create table Adv_class(_advClassID integer primary key, _classID integer, advClassName text, weaponID1 integer ,weaponID2 integer, weaponID3 integer, armorTypeID integer);
create table Class(_classID integer primary key, className text, faction text);
create table Crew_skill(_crewSkillID integer primary key, crewSkillName text, crewSkillTypeID integer);
create table Crew_skill_types(_crewSkillTypeID integer primary key, crewSkillTypeName text);
create table Crew_skill_yields(_crewSkillYieldsID integer primary key, crewSkillID integer, crewSkillYieldsName text);

create table Crew_skill_needs(toon text,yield text, grade text,count text,name text);

-- This is a list of which classes have which types of companions.
create table Class_companion_map(_classID integer, _companionTypeID integer);



-- Listing of armor types.  the type field is something like heavy, medium,  light
-- or implant.
create table Armor_type(_armorTypeID integer primary key, armorType text);
create table Weapon_type(_weaponTypeID integer primary key, weaponType text);
-- Listing of primary stats.  It is tied to the armour and toons/companions
-- The name text field corresponds to things like "strength", "Endurance", "Aim", ...
create table Primary_stat(_pstatID integer primary key, name text, note1 text, note2 text);
create table Secondary_stat(_sstatID integer primary key, name text, note1 text, note2 text);
-- Listing of armor for a slot.
-- The primary stat is the highest stat for this piece. If it has +5 str and +3 end, the _statID entry would correspond to strength.
create table Armor(_armorID integer primary key, _armorTypeID integer, slotID integer, level integer, _statID integer, hasSockets boolean default 0, hasAugments boolean default 0, rating integer);
-- These are the equipable slots for armor.  The name corresponds to "Head", "Chest", etc.
create table Armor_slot(_armorSlotID integer primary key, name text);
-- Listing of armor for a slot.
-- The primary stat is the highest stat for this piece. If it has +5 str and +3 end, the _statID entry would correspond to strength.
create table Weapon(_weaponID integer primary key, _weaponTypeID integer, slot1ID integer, slot2ID integer, level integer, _statID integer, hasSockets boolean default 0, hasAugments boolean default 0, rating integer);
create table Weapon_slot(_weaponSlotID integer primary key, name text);


-- Companion things.
create table Companion_opinion(_companionTypeID integer, opinion text, likes boolean); -- 1==like 0=dislikes
create table Companion_type(_companionTypeID integer primary key, companionName text, _factionID integer, pic blob);
-- I should rewrite this so that its one table and the high, medium and low are stored in a column.
create table Gifts(_giftID integer primary key, gift_name text);
-- todo: gifts tables with a high, medium, low setting for quality field. 1 is highest
create table Companion_gift_prefs(_companionTypeID integer, _giftID integer, preference integer);

create table Companion_crewskill_bonus(_companionTypeID integer primary key, major text, minor text);
create table Companion_stats(_companionTypeID integer primary key, primarystat text, armor text, weapon text);
create table Companion_notes(_companionTypeID integer primary key, note1 text, note2 text, note3 text);

create table Todo_list(_toonID integer primary key, isDone boolean default 0, _todoID integer);
create table Todo_list_items(_todoID integer primary key, todoName text);


-- This is the android database "tweak"
create table android_metadata("locale" TEXT DEFAULT 'en_US');
insert into android_metadata values('en_US');
