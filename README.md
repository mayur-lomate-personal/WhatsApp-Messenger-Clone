# Load Balanced WebSocket with Authorization and Rabbitmq message broker to send messages to a particular user

This is a WhatsApp message clone to send messages to a particular user as well as option to broadcast it.

## Run

```docker
docker compose up -id
```

## APIs

POST localhost:9080/api/register/user :- To create new user

POST localhost:9080/api/jwt :- To get jwt token

GET localhost:9080/api/websocket/session :- to get session token valid for 30 seconds, used for websocket. 

## System Design
![websocket-system-design](https://user-images.githubusercontent.com/77961230/197375506-368fdc6a-5d72-4141-aa7a-e832c400ae36.jpg)
