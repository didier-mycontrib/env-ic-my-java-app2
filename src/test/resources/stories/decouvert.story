Narrative: le decouvert est autorisé jusqu'à un maximum négatif (constante DECOUVERT_AUTORISE)

Scenario: debit couvert

Given solde=100 positif
When avec 0< montant=70 <=solde
And debiter(montant=70)
Then debit accepte 
And statut = OK


Scenario: decouvert autorise

Given solde=100 positif
When avec montant=200>solde et solde-montant >= DECOUVERT_AUTORISE
And debiter(montant=200)
Then debit accepte 
And statut = A_DECOUVERT


Scenario: decouvert non autorise

Given solde=-50 superieur au decouvert autorise
When debiter(montant=600) avec solde-montant < DECOUVERT_AUTORISE
Then debit refuse (exception) 
And solde inchange