kubectl wait --for=condition=available --timeout=300s deployment mysql
kubectl apply -f tomcat-deployment.yaml
kubectl apply -f tomcat-service.yaml
