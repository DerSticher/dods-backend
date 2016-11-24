--
-- Daten für Tabelle `aspekt`
--

INSERT INTO `aspekt` (`id`, `name`) VALUES
(1, 'allgemein'),
(2, 'Antimagie (Praios)'),
(3, 'Handel (Phex)'),
(4, 'Heilung (Peraine)'),
(5, 'Landwirtschaft (Peraine)'),
(6, 'Magie (Hesinde)'),
(7, 'Ordnung (Praios)'),
(8, 'Schatten (Phex)'),
(9, 'Schild (Rondra)'),
(10, 'Sturm (Rondra)'),
(11, 'Tod (Boron)'),
(12, 'Traum (Boron)'),
(13, 'Wissen (Hesinde)');

--
-- Daten für Tabelle `dauer`
--

INSERT INTO `dauer` (`id`, `name`) VALUES
(1, '1 Aktion'),
(2, '2 Aktionen'),
(3, '4 Aktionen'),
(4, '8 Aktionen'),
(5, '16 Aktionen'),
(6, '32 Aktionen'),
(7, '30 Minuten'),
(8, '2 Stunden'),
(9, '8 Stunden');

--
-- Daten für Tabelle `wirkungsdauer`
--

INSERT INTO `wirkungsdauer` (`id`, `name`) VALUES
(1, 'sofort'),
(2, '1 Aktion'),
(3, '1 Minute'),
(4, '5 Minuten'),
(5, '30 Minuten'),
(6, 'QS x 2 in Minuten'),
(7, 'QS x 3 in Minuten'),
(8, 'QS x 15 in Minuten'),
(9, 'QS x 3 in Stunden'),
(10, 'QS x 3 in Tagen'),
(11, 'QS x 3 in Wochen'),
(12, '5 Kampfrunden'),
(13, 'QS in Kampfrunden'),
(14, 'QS x 3 in Kampfrunden'),
(15, 'QS x 5 in Kampfrunden'),
(16, 'Bis zum nächsten Schuss, maximal QS x 2 Kampfrunden');

--
-- Daten für Tabelle `zielkategorie`
--

INSERT INTO `zielkategorie` (`id`, `name`) VALUES
(1, 'Wesen'),
(2, 'Lebewesen'),
(3, 'Kulturschaffende'),
(4, 'Tiere'),
(5, 'Pflanzen'),
(6, 'Pilze'),
(7, 'übernatürliche Lebewesen'),
(8, 'Feen'),
(9, 'Chimären'),
(10, 'Drachen'),
(11, 'Daimonide'),
(12, 'Nicht Lebende'),
(13, 'Untote'),
(14, 'Geister'),
(15, 'Hirnlose'),
(16, 'Vampire'),
(17, 'Beseelte'),
(18, 'Dämonen'),
(19, 'Elementare'),
(20, 'Golems'),
(21, 'Objekte'),
(22, 'Profane Objekte'),
(23, 'Magische Objekte'),
(24, 'Geweihte Objekte');

--
-- Daten für Tabelle `attribut`
--

INSERT INTO `attribut` (`typ`, `id`, `name`) VALUES
('Eigenschaft', 1, 'Mut'),
('Eigenschaft', 2, 'Klugheit'),
('Eigenschaft', 3, 'Intuition'),
('Eigenschaft', 4, 'Charisma'),
('Eigenschaft', 5, 'Fingerfertigkeit'),
('Eigenschaft', 6, 'Geschicklichkeit'),
('Eigenschaft', 7, 'Konstitution'),
('Eigenschaft', 8, 'Körperkraft'),

('Fertigkeit', 9, 'Fliegen'),
('Fertigkeit', 10, 'Gaukeleien'),
('Fertigkeit', 11, 'Klettern'),
('Fertigkeit', 12, 'Körperbeherrschung'),
('Fertigkeit', 13, 'Kraftakt'),
('Fertigkeit', 14, 'Reiten'),
('Fertigkeit', 15, 'Schwimmen'),
('Fertigkeit', 16, 'Selbstbeherrschung'),
('Fertigkeit', 17, 'Singen'),
('Fertigkeit', 18, 'Sinnesschärfe'),
('Fertigkeit', 19, 'Tanzen'),
('Fertigkeit', 20, 'Taschendiebstahl'),
('Fertigkeit', 21, 'Verbergen'),
('Fertigkeit', 22, 'Zechen'),

('Fertigkeit', 23, 'Bekehren & Überzeugen'),
('Fertigkeit', 24, 'Betören'),
('Fertigkeit', 25, 'Einschüchtern'),
('Fertigkeit', 26, 'Etikette'),
('Fertigkeit', 27, 'Gassenwissen'),
('Fertigkeit', 28, 'Menschenkenntnis'),
('Fertigkeit', 29, 'Überreden'),
('Fertigkeit', 30, 'Verkleiden'),
('Fertigkeit', 31, 'Willenskraft'),

('Fertigkeit', 32, 'Fährtensuchen'),
('Fertigkeit', 33, 'Fesseln'),
('Fertigkeit', 34, 'Fischen & Angeln'),
('Fertigkeit', 35, 'Orientierung'),
('Fertigkeit', 36, 'Pflanzenkunde'),
('Fertigkeit', 37, 'Tierkunde'),
('Fertigkeit', 38, 'Wildnisleben'),

('Fertigkeit', 39, 'Brett- & Glücksspiel'),
('Fertigkeit', 40, 'Geographie'),
('Fertigkeit', 41, 'Geschichtswissen'),
('Fertigkeit', 42, 'Götter & Kulte'),
('Fertigkeit', 43, 'Kriegskunst'),
('Fertigkeit', 44, 'Magiekunde'),
('Fertigkeit', 45, 'Mechanik'),
('Fertigkeit', 46, 'Rechnen'),
('Fertigkeit', 47, 'Rechtskunde'),
('Fertigkeit', 48, 'Sagen & Legenden'),
('Fertigkeit', 49, 'Sphärenkunde'),
('Fertigkeit', 50, 'Sternkunde'),

('Fertigkeit', 51, 'Alchimie'),
('Fertigkeit', 52, 'Boote & Schiffe'),
('Fertigkeit', 53, 'Fahrzeuge'),
('Fertigkeit', 54, 'Handel'),
('Fertigkeit', 55, 'Heilkunde Gift'),
('Fertigkeit', 56, 'Heilkunde Krankheiten'),
('Fertigkeit', 57, 'Heilkunde Seele'),
('Fertigkeit', 58, 'Heilkunde Wunden'),
('Fertigkeit', 59, 'Holzbearbeitung'),
('Fertigkeit', 60, 'Lebensmittelbearbeitung'),
('Fertigkeit', 61, 'Lederbearbeitung'),
('Fertigkeit', 62, 'Malen & Zeichnen'),
('Fertigkeit', 63, 'Metallbearbeitung'),
('Fertigkeit', 64, 'Musizieren'),
('Fertigkeit', 65, 'Schlösserknacken'),
('Fertigkeit', 66, 'Steinbearbeitung'),
('Fertigkeit', 67, 'Stoffbearbeitung');

--
-- Daten für Tabelle `eigenschaft`
--

INSERT INTO `eigenschaft` (`kostentabelle`, `id`) VALUES
('E', 1),
('E', 2),
('E', 3),
('E', 4),
('E', 5),
('E', 6),
('E', 7),
('E', 8);

--
-- Daten für Tabelle `fertigkeit`
--

INSERT INTO `fertigkeit` (`id`, `gruppe`, `kostentabelle`, `teilprobe1_id`, `teilprobe2_id`, `teilprobe3_id`) VALUES
(9, 'KOERPER', 'B', 1, 3, 6),
(10, 'KOERPER', 'A', 1, 4, 5),
(11, 'KOERPER', 'B', 1, 6, 8),
(12, 'KOERPER', 'D', 6, 6, 7),
(13, 'KOERPER', 'B', 7, 8, 8),
(14, 'KOERPER', 'B', 4, 6, 8),
(15, 'KOERPER', 'B', 6, 7, 8),
(16, 'KOERPER', 'D', 1, 1, 7),
(17, 'KOERPER', 'A', 2, 4, 7),
(18, 'KOERPER', 'D', 2, 3, 3),
(19, 'KOERPER', 'A', 2, 4, 6),
(20, 'KOERPER', 'B', 1, 5, 6),
(21, 'KOERPER', 'C', 1, 3, 6),
(22, 'KOERPER', 'A', 2, 7, 8),

(23, 'GESELLSCHAFT', 'B', 1, 2, 4),
(24, 'GESELLSCHAFT', 'B', 1, 4, 4),
(25, 'GESELLSCHAFT', 'B', 1, 3, 4),
(26, 'GESELLSCHAFT', 'B', 2, 3, 4),
(27, 'GESELLSCHAFT', 'C', 2, 3, 4),
(28, 'GESELLSCHAFT', 'C', 2, 3, 4),
(29, 'GESELLSCHAFT', 'C', 1, 3, 4),
(30, 'GESELLSCHAFT', 'B', 3, 4, 6),
(31, 'GESELLSCHAFT', 'D', 1, 3, 4),

(32, 'NATUR', 'C', 1, 3, 6),
(33, 'NATUR', 'A', 2, 5, 8),
(34, 'NATUR', 'A', 5, 6, 7),
(35, 'NATUR', 'B', 2, 3, 3),
(36, 'NATUR', 'C', 2, 5, 7),
(37, 'NATUR', 'C', 1, 1, 7),
(38, 'NATUR', 'C', 1, 6, 7),

(39, 'WISSEN', 'A', 2, 2, 3),
(40, 'WISSEN', 'B', 2, 2, 3),
(41, 'WISSEN', 'B', 2, 2, 3),
(42, 'WISSEN', 'B', 2, 2, 3),
(43, 'WISSEN', 'B', 1, 2, 3),
(44, 'WISSEN', 'C', 2, 2, 3),
(45, 'WISSEN', 'B', 2, 2, 5),
(46, 'WISSEN', 'A', 2, 2, 5),
(47, 'WISSEN', 'A', 2, 2, 3),
(48, 'WISSEN', 'B', 2, 2, 3),
(49, 'WISSEN', 'B', 2, 2, 3),
(50, 'WISSEN', 'A', 2, 2, 3),

(51, 'HANDWERK', 'C', 1, 2, 5),
(52, 'HANDWERK', 'B', 5, 6, 8),
(53, 'HANDWERK', 'A', 4, 5, 7),
(54, 'HANDWERK', 'B', 2, 3, 4),
(55, 'HANDWERK', 'B', 1, 2, 3),
(56, 'HANDWERK', 'B', 1, 3, 7),
(57, 'HANDWERK', 'B', 3, 4, 7),
(58, 'HANDWERK', 'D', 2, 5, 5),
(59, 'HANDWERK', 'B', 5, 6, 8),
(60, 'HANDWERK', 'A', 3, 5, 5),
(61, 'HANDWERK', 'B', 5, 6, 7),
(62, 'HANDWERK', 'A', 3, 5, 5),
(63, 'HANDWERK', 'C', 5, 7, 8),
(64, 'HANDWERK', 'A', 4, 5, 7),
(65, 'HANDWERK', 'C', 3, 5, 5),
(66, 'HANDWERK', 'A', 5, 5, 8),
(67, 'HANDWERK', 'A', 2, 5, 5);
