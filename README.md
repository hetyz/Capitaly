# Capitaly
ELTE IK, Programtervező Informatikus, Programozási Technológia, 1. beadandó 

Feladat:\
Szimuláljuk az alábbi egyszerűsített Capitaly társasjátékot! Adott néhány eltérő stratégiájú játékos és
egy körpálya, amelyen különféle mezők sorakoznak egymás után. A pályát körbe-körbe újra és újra
bejárják a játékosok úgy, hogy egy kockával dobva mindig annyit lépnek, amennyit a kocka mutat. A
mezők három félék lehetnek: ingatlanok, szolgáltatások és szerencse mezők. Az ingatlant meg lehet
vásárolni 1000 Petákért, majd újra rálépve házat is lehet rá építeni 4000 Petákért. Ha ezután más
játékos erre a mezőre lép, akkor a mező tulajdonosának fizet: ha még nincs rajta ház, akkor 500
Petákot, ha van rajta ház, akkor 2000 Petákot. A szolgáltatás mezőre lépve a banknak kell befizetni a
mező paramétereként megadott összeget. A szerencse mezőre lépve a mező paramétereként
megadott összegű pénzt kap a játékos. Háromféle stratégiájú játékos vesz részt a játékban.
Kezdetben mindenki kap egy induló tőkét (10000 Peták), majd a „mohó” játékos ha egy még
gazdátlan ingatlan mezőjére lépett, vagy övé az ingatlan, de még nincs rajta ház, továbbá van elég
tőkéje, akkor vásárol. Az „óvatos” játékos egy körben csak a tőkéjének a felét vásárolja el, a
„taktikus” játékos minden második vásárlási lehetőséget kihagyja. Ha egy játékosnak fizetnie kell, de
nincs elégendő pénze, akkor kiesik a játékból, házai elvesznek, ingatlanjai megvásárolhatókká válnak.
A játék paramétereit egy szövegfájlból olvassuk be. Ez megadja a pálya hosszát, majd a pálya egyes
mezőit. Minden mezőről megadjuk annak típusát, illetve ha szolgáltatás vagy szerencse mező, akkor
annak pénzdíját. Ezt követően a fájl megmutatja a játékosok számát, majd sorban minden játékos
nevét és stratégiáját. A tesztelhetőséghez fel kell készíteni a megoldó programot olyan szövegfájl
feldolgozására is, amely előre rögzített módon tartalmazza a kockadobások eredményét. Írjuk ki,
melyik játékos esik ki másodszorra a játékból!
