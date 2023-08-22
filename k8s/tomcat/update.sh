kubectl set image deployment/tomcat tomcat=rlopezb/medicare-be:latest
kubectl wait --for=condition=available --timeout=300s deployment tomcat
kubectl port-forward --address 0.0.0.0 service/tomcat 8080:8080 > /dev/null 2>&1 &
