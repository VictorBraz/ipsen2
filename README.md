# ipsen2
SE project 2

!!! Graag niks in Master schrijven, alles in eigen, local branch !!!

# Database Connection:

Maak een nieuw Database in Postgresql en noem deze: HubSpot
Open vervolgens Database.java via IntelliJ en verander hierin je gebruikersnaam en wachtwoord voor de database.
Download vervolgens de driver voor postgres via de volgende link: https://jdbc.postgresql.org/download/postgresql-9.4.1211.jre6.jar
Ga naar IntelliJ ->File->Project Structure->Libraries en klik op de groene +. Zoek vervolgens de driver op en voeg deze toe als Java. Klik op Apply en daarna OK.

# GIT commando's

Om te beginnen pull master vanuit de terminal:
  >git pull origin master

Maak vervolgens een eigen branch aan:
  >git checkout -b "Eigen-branch-naam"
  
Deze branch is local en je bent de enige die hier op werkt. 
Om de branch Remote te maken, dus op GitHub zetten zodat je je werk opslaat doe je:
  >git push -u origin "Eigen-branch-naam"

Elke keer dat je weer verder gaat werken in die branch doe je:
  >git pull origin "Eigen-branch-naam"

