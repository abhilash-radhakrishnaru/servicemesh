# This project demos following concepts
1) Spring MVC, SpringBoot, SpringWeb
2) Docker container
3) compile & build docker containers using maven
4) kubernetes deployment
5) istio
6) traffic management using istio
7) kiali dashboard
8) distributed tracing using zipkin/jaeger

# Flow

![flow.png](flow.png)

#Installation
1) install k8s, docker
   
2) install istio<br>
   $ curl -L https://istio.io/downloadIstio | sh - <br>
   $ cd istio-1.11.0 <br>
   $ istioctl install --set profile=demo -y
3)  Add label so that istio will push envoy proxy sidecar. "kubectl label namespace default istio-injection=enabled"

# Deployment
1) Build all 5 apps (maven build). This will create docker container images
2) push the images to docker registry using docker push (eg: docker push abhilash/ecom-ui:1.6)
3) Deploy work loads, services, service accounts, ingress & virtual service to k8s(kubectl apply -f <all files in yamls folder>)
4) Run "istioctl analyze" to make sure everything is fine
5) Apply prometheus, kiali, zipkin, grafana & jaeger yaml files
6) deploy kiali using "kubectl rollout status deployment/kiali -n istio-system"<br>
   refer this link for all yaml files => https://github.com/istio/istio/tree/release-1.11/samples/addons
7) to access kiali use "istioctl dashboard kiali". This will lauch kiali in browser

#Accessing the page
- get ip address using minikube ip (if its minikube running on virtual box). If not, need to get the ip to cluster
- get port number using "kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}'" (otherwise get the physical port binded)
access the page using http://<ip>:<port>/home