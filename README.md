# API-PFE

## API Dévelopée dans le cadre d'un projet de fin d'étude à l'Institut Paul Lambin (IPL)

Composée d'un ensemble de routes recouvrant les fonctionnalités du front-end (voir documentation Swagger) et la logique business.

Utilise la librairie Jersey(2.X) et est donc déployable dans un container Jetty (sur Heroku dans ce cas précis).

La communication est faite en utilisant des requêtes HTTP/HTTPS et principalement les méthodes GET et POST, le format JSON est utilisé 
pour la transmission des données.

La communication des résultats de requêtes est gérée par le standard de Status Code Web et par un objet Response définit dans Jersey.

