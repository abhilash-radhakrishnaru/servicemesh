# Read Me
 This service returns the price of requested product
 
This will create a docker image for the pricer app
image name : pricer-api
tag : version of the app

App will be running on port 9080 inside the container

Execute below command to run the container
    docker run -p <external port>>:<port which app is running inside the conatiner> pricer-api:<version>
    eg: docker run -p 9004:9080 pricer-api:1.0
Access the app using the URL(locally)
    http://localhost:9004/wprice