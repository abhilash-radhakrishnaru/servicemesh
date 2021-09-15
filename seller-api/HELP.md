# Read Me
 This service returns the details of products seller
 
This will create a docker image for the seller details app
image name : seller-api
tag : version of the app

App will be running on port 9080 inside the container

Execute below command to run the container
    docker run -p <external port>>:<port which app is running inside the conatiner> seller-api:<version>
    eg: docker run -p 9002:9080 -e STOCK_SERVICE=localhost -e STOCK_PORT=9003 -e PRICER_SERVICE=localhost -e PRICER_PORT=9004 seller-api:1.0  
Access the app using the URL(locally)
    http://localhost:9002/seller/washingmachine