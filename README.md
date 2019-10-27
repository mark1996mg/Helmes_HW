# Helmes_HW

Kasutasin veebirakenduse tegemiseks:
IDE: Eclipse (uusim versioon),
Server: Apache Tomcat v9.0,
Java versioon: JDK 12
Kasutatud andmebaas: PostgreSQL: 11

Paigaldusjuhend

1) Andmebaasi dump asub HelmesWebApp/src/main/resources/pg_dump kaustas.
   Andmebaasi sätted (kasutaja, nimi, parool) asub failis HelmesWebApp/src/main/webapp/WEB-INF/helmes-webapp-servlet.xml
2) Importida Maven projekt -> HelmesWebApp (Eclipse'is File -> Import... -> Maven -> Existing Maven Project -> HelmesWebApp)
3) Lisada uus server (kasutasin Tomcat'i)
4) Vajadusel määrata ka sobiv Java JDK versioon (Eclipse'is HelmesWebApp-> Properties -> Project Facets -> valida (sobilik) JDK)
5) Vajadusel update'da Maven projekt (HelmesWebApp -> Maven -> update project)
6) Käivitada server ja rakendus peaks nüüd töötama

Veebirakenduse kirjeldus

Lehe avamisel kuvatakse vaade (overview), kus esialgu pole andmeid.
Andmete lisamiseks tuleb vajutada nupule Edit, siis avatakse aken, kus saab sisestada kasutaja nime(kohustuslik väli),
valida tegevusalu (sectors, tegevusalad saadakse andmebaasist ning vähemalt üks peab olema valitud) ja nõustuda tingimustega (kohustuslik). Akna saab, kas kinni panna või siis salvestada muudatused. Salvestatud muudatused kuvatakse ka ülevaate raamis (overview). Antud sessiooni käigus saab oma andmeid muuta kui vajutada uuesti nupule Edit siis kuvatakse aknas eelnevad valitud/sisestatud väärtused, mida saab muuta ja siis kinnitada muudatused vajutades nupule Save, mis siis salvestab muudatused andmebaasi.
