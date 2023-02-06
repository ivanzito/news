# Headline news

Get headlines for your service. You can get the news headlines in two manners,
one by ***SSE***(server sent events) and other by the famous ***REST***

### How to start the application

Using only docker you should use the command below. It's necessary a MongoDB up too

    docker run -p 8080:8080 --network=host news-headline

Or you can only use the *docker-compose.yaml* and execute the code below

    docker-compose up

### How to get headlines

***Attention!***

Only responses by json can be accessed by Browser, if you wanna a response
with ___SSE___ you should pass by header a **Content-Type** = **text/plain** and if you're 
using the api for development purposes, to call endpoints and expect 
a json output, you don't need pass a ___Content-Type___

## Endpoints available

- http://localhost:8080/news/headlines
- http://localhost:8080/news/headlines?keywork=something
- http://localhost:8080/news/headlines?keywork=something&category=business



