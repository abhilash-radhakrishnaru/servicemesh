# Read Me
 This is the user facing side
 
This will create a docker image for the ecom app
image name : ecom-ui
tag : version of the app

App will be running on port 9080 inside the container

Execute below command to run the container
    docker run -p <external port>>:<port which app is running inside the conatiner> ecom-ui:<version>
    eg: docker run -p 9081:9080 ecom-ui:1.0
Access the app using the URL(locally)
    http://localhost:9081/home