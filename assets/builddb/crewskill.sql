/* Populate the tables with our data */
insert into Crew_skill_types values(201, "Gathering");
insert into Crew_skill_types values(202, "Crafting");
insert into Crew_skill_types values(203, "Missions");

-- max of three
insert into Crew_skill values(101, "Archaeology",201);
insert into Crew_skill values(102, "Bioanalysis",201);
insert into Crew_skill values(103, "Scavenging",201);
insert into Crew_skill values(104, "Slicing",201);

-- Archaeology
insert into Crew_skill_yields values(301, 101, "Artifact Fragment");
insert into Crew_skill_yields values(302, 101, "Color Crystals");
insert into Crew_skill_yields values(303, 101, "Power Crystals");

-- Bioanalysis
insert into Crew_skill_yields values(304, 102, "Biochemical Compounds");
insert into Crew_skill_yields values(305, 102, "Biochemical Samples");

-- scavenging
insert into Crew_skill_yields values(306, 103, "Scavenged Compounds");
insert into Crew_skill_yields values(307, 103, "Scavenged Metals");
insert into Crew_skill_yields values(308, 103, "Flux");

-- Slicing
insert into Crew_skill_yields values(309, 104, "Augments");
insert into Crew_skill_yields values(310, 104, "Lockboxes");
insert into Crew_skill_yields values(311, 104, "Mission Discoveries");
insert into Crew_skill_yields values(312, 104, "Cybertech Schematics");

-- max of one
insert into Crew_skill values(105, "Armortech",202);
insert into Crew_skill values(106, "Armstech",202);
insert into Crew_skill values(107, "Artifice",202);
insert into Crew_skill values(108, "Biochem",202);
insert into Crew_skill values(109, "Cybertech",202);
insert into Crew_skill values(110, "Synthweaving",202);

-- Armortech
insert into Crew_skill_yields values(313, 105, "Medium armor");
insert into Crew_skill_yields values(314, 105, "Heavy armor");


-- Armstech
insert into Crew_skill_yields values(315, 106, "Blaster Pistol");
insert into Crew_skill_yields values(316, 106, "Blaster Rifle");
insert into Crew_skill_yields values(317, 106, "Assault Cannon");
insert into Crew_skill_yields values(318, 106, "Sniper Rifle");
insert into Crew_skill_yields values(319, 106, "Vibrosword");
insert into Crew_skill_yields values(320, 106, "Vibroknife");
insert into Crew_skill_yields values(321, 106, "Electrostaff");
insert into Crew_skill_yields values(322, 106, "Techstaff");
insert into Crew_skill_yields values(323, 106, "Techblade");
insert into Crew_skill_yields values(324, 106, "Scattergun");
insert into Crew_skill_yields values(325, 106, "Weapon Barrels");

-- Artifice
insert into Crew_skill_yields values(326, 107, "Lightsaber");
insert into Crew_skill_yields values(327, 107, "Double-bladed Lightsaber");
insert into Crew_skill_yields values(328, 107, "Lightsaber Hilt");
insert into Crew_skill_yields values(329, 107, "Lightsaber Crystal");
insert into Crew_skill_yields values(330, 107, "Enhancements");
insert into Crew_skill_yields values(331, 107, "Generators");
insert into Crew_skill_yields values(332, 107, "Shields");
insert into Crew_skill_yields values(333, 107, "Focus Crystals");

-- Biochem
insert into Crew_skill_yields values(334, 108, "Medpacs");
insert into Crew_skill_yields values(335, 108, "Reusable Medpacs");
insert into Crew_skill_yields values(336, 108, "Stims");
insert into Crew_skill_yields values(337, 108, "Reusable Stims");
insert into Crew_skill_yields values(338, 108, "Implants");

-- Cybertech
insert into Crew_skill_yields values(339, 109, "Armoring");
insert into Crew_skill_yields values(340, 109, "Mods");
insert into Crew_skill_yields values(341, 109, "Ear Pieces");
insert into Crew_skill_yields values(342, 109, "Droid Parts");
insert into Crew_skill_yields values(343, 109, "Ship Mods");
insert into Crew_skill_yields values(344, 109, "Bombs");
insert into Crew_skill_yields values(345, 109, "Speeders");
insert into Crew_skill_yields values(346, 109, "Gadgets");

-- Synthweaving
insert into Crew_skill_yields values(347, 110, "lht force armor");
insert into Crew_skill_yields values(363, 110, "med force armor");
insert into Crew_skill_yields values(348, 110, "hvy force armor");


-- max of three
insert into Crew_skill values(111, "Diplomacy",203);
insert into Crew_skill values(112, "Investigation",203);
insert into Crew_skill values(113, "Treasure Hunting",203);
insert into Crew_skill values(114, "Underworld Trading",203);

-- Diplomacy
insert into Crew_skill_yields values(349, 111, "Light Side Points");
insert into Crew_skill_yields values(350, 111, "Dark Side Points");
insert into Crew_skill_yields values(351, 111, "Medical Supplies");
insert into Crew_skill_yields values(352, 111, "Companion Gifts");

-- Investigation
insert into Crew_skill_yields values(353, 112, "Researched Compounds");
insert into Crew_skill_yields values(354, 112, "Prototype Schematics");
insert into Crew_skill_yields values(355, 112, "Companion Gifts");

-- Treasure Hunting
insert into Crew_skill_yields values(356, 113, "Gemstones");
insert into Crew_skill_yields values(357, 113, "Lockboxes");
insert into Crew_skill_yields values(358, 113, "Companion Gifts");

-- Underworld Trading
insert into Crew_skill_yields values(359, 114, "Underworld Metal");
insert into Crew_skill_yields values(360, 114, "Luxury Fabric");
insert into Crew_skill_yields values(361, 114, "Custom schematics");
insert into Crew_skill_yields values(362, 114, "Companion Gifts");





