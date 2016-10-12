# ipsen2
SE project 2

!!! Graag niks in Master schrijven, alles in eigen, local branch !!!

# Database Connection:

Maak een nieuw Database in Postgresql en noem deze: HubSpot
Open vervolgens Database.java via IntelliJ en verander hierin je gebruikersnaam en wachtwoord voor de database.
Download vervolgens de driver voor postgres via de volgende link: https://drive.google.com/open?id=0B6VJaMVf48YiRHhGaEZlYkFGV0U
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

Om je branch te pushen naar de eerder aangemaakte remote doe je de volgende commando's afzonderlijk van elkaar:
  >git add .                     //deze commando voegt alle aangepaste file toe aan git zodat je deze kan commiten
  
  >git commit -a -m "..."        //hiermee commit je al(-a) je aangepaste files op je branch. -m staat voor Message en binnen de                                          //aanhalingstekens moet je een message typen over de commit. Wat de aanpassingen/toevoegingen zijn                                      //bijvoorbeeld. Je bent verplicht een message mee te geven anders werkt je commando niet.
  >git push 

Zorg ervoor dat je branch altijd up-to-date is met master, dus pull master in je branch elke keer dat master verandert.
Dit voorkomt problemen als je je branch(werk) in master wil zetten. Ook is dit handig zodat je je werk met master kan testen.

# Mergen met master

Om te mergen met master voer de volgende commando's afzonderlijk van elkaar:
  >git checkout master
  >git pull
  >git merge "brachnaam"
  
Run vervolgens de applicatie om te testen of het echt werkt. Als het werkt doe je:
  >git push
