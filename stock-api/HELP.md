# Read Me
 This service returns the available stock of requested product
 
This will create a docker image for the stock details app
image name : stock-details-api
tag : version of the app

App will be running on port 9080 inside the container

Execute below command to run the container
    docker run -p <external port>>:<port which app is running inside the conatiner> stock-details-api:<version>
    eg: docker run -p 9003:9080 stock-details-api:1.0
Access the app using the URL(locally)
    http://localhost:9003/wstock

TODO:Currently the app just returns a static value. Need to integrate with some persistence storage like file/DB. 