# Erweiterung & Verbesserung von Stefan Leithenmayr (Katalognummer 12)

## Erweiterung
<div>
    <p>Erweiterung, sodass keine Matches in das Set doppelt eingefügt werden</p>
    <img src="./images/Extension1.png" align="left" border="1px" width="50%" height="auto">
    <br clear="all">
    <img src="./images/Extension2.png" align="left" border="1px" width="50%" height="auto">
    <br clear="all">
</div>

<div>
    <p>Erweiterung, sodass keine Matches doppelt in die Datenbank eingefügt werden (Klasse: MatchDao)</p>
    <img src="./images/Extension3.png" align="left" border="1px" width="22%" height="auto">
    <br clear="all">
    <img src="./images/Extension4.png" align="left" border="1px" width="50%" height="auto">
    <br clear="all">
</div>

<div>
    <p>Erweiterung, sodass keine Teams doppelt in die Datenbank eingefügt werden (Klasse: TeamDao)</p>
    <img src="./images/Extension5.png" align="left" border="1px" width="22%" height="auto">
    <br clear="all">
    <img src="./images/Extension6.png" align="left" border="1px" width="50%" height="auto">
    <br clear="all">
</div>

## Verschieben der Initialisierung in den Konstruktor
<div>
    <p>Alter Code:</p>
    <img src="./images/DaoInit_Before.png" align="left" border="1px" width="30%" height="auto">
    <br clear="all">
    <p>Neuer Code:</p>
    <img src="./images/DaoInit_After.png" align="left" border="1px" width="30%" height="auto">
    <br clear="all">
</div>

## Verwenden der Equal Methode
<div>
    <p>Da ich die Equal Methode der Entität Team bereits überschrieben habe sollte man diese auch verwenden</p>
    <img src="./images/ChangedIfCondition.png" align="left" border="1px" width="90%" height="auto">
    <br clear="all">
</div>

## Anpassung der Sortierung
<div>
    <img src="./images/FalseSorting.png" align="left" border="1px" width="60%" height="auto">
    <br clear="all">
</div>

## Änderung in der Forschleife
<div>
    <img src="./images/ForLoop_Edit.PNG" align="left" border="1px" width="60%" height="auto">
    <br clear="all">
</div>

## Verwendung einer Variable und kein mehrfach Aufruf

<div>
    <p>Wird verändert da die Methode getResultLine bzw. .Contains ansonsten mehrfach aufgerufen wird und jetzt ruft man die Methode nur zweimal auf und .Contains wird nicht mehr benötigt</p>
    <br clear="all">
    <p>Alter Code:</p>
    <img src="./images/MultipleVariables_Before.png" align="left" border="1px" width="60%" height="auto">
    <br clear="all">
    <p>Neuer Code:</p>
    <img src="./images/MultipleVariables_After.png" align="left" border="1px" width="60%" height="auto">
    <br clear="all">
</div>
