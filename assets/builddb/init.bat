@echo off

rem 
rem
rem  
rem  
rem  
rem NOTES:
rem 
echo ===============1
sqlite3 swtorlab.db < Schema.sql
sqlite3 swtorlab.db < class.sql
sqlite3 swtorlab.db < crewskill.sql
sqlite3 swtorlab.db < stat.sql
sqlite3 swtorlab.db < armors.sql
sqlite3 swtorlab.db < weapons.sql
sqlite3 swtorlab.db < gifts.sql
rem these are companions
sqlite3 swtorlab.db < aricjorgan.sql
sqlite3 swtorlab.db < kaliyodjannis.sql
sqlite3 swtorlab.db < Khemval.sql
sqlite3 swtorlab.db < mako.sql
sqlite3 swtorlab.db < qyzenfess.sql
sqlite3 swtorlab.db < Vette.sql
sqlite3 swtorlab.db < Quinn.sql
sqlite3 swtorlab.db < Vector.sql
sqlite3 swtorlab.db < Gault.sql
sqlite3 swtorlab.db < Revel.sql
sqlite3 swtorlab.db < Jaesa.sql
sqlite3 swtorlab.db < Ashara.sql
sqlite3 swtorlab.db < drlokin.sql
sqlite3 swtorlab.db < LTPierce.sql
sqlite3 swtorlab.db < Torian.sql
sqlite3 swtorlab.db < Blizz.sql
sqlite3 swtorlab.db < Raina.sql
sqlite3 swtorlab.db < Talos.sql
sqlite3 swtorlab.db < Broonmark.sql
sqlite3 swtorlab.db < Scorpio.sql
sqlite3 swtorlab.db < Xalek.sql
sqlite3 swtorlab.db < Skadge.sql
sqlite3 swtorlab.db < Tharan.sql
sqlite3 swtorlab.db < Shipdroid.sql
rem these are my toons
sqlite3 swtorlab.db < mytoons.sql
rem the todo list
sqlite3 swtorlab.db < todolist.sql

rem sqlite3 swtorlab.db ".schema Companions"
copy /y swtorlab.db ..



