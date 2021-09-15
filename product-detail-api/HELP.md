# Read Me
 This service returns the details of requested products
 
This will create a docker image for the product details app
image name : product-details-app
tag : version of the app

App will be running on port 9080 inside the container

Execute below command to run the container
    docker run -p <external port>>:<port which app is running inside the conatiner> product-details-api:<version>
    eg: docker run -p 9001:9080 -e SELLER_SERVICE=localhost -e SELLER_PORT=9002 product-details-api:1.0
Access the app using the URL(locally)
    http://localhost:9001/details/washingmachine

Disclaimer : No Exceptions handled