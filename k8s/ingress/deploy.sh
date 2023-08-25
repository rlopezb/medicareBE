kubectl wait --for=condition=available --timeout=300s --all deployment
minikube addons enable ingress
kubectl apply -f minikube-ingressClass.yaml
kubectl apply -f minikube-ingress.yaml
kubectl port-forward --address 0.0.0.0 service/nginx 8008:80 > /dev/null 2>&1 &
kubectl port-forward --address 0.0.0.0 service/tomcat 8080:8080 > /dev/null 2>&1 &
#kubectl port-forward --address 0.0.0.0 service/jenkins 8081:8081 > /dev/null 2>&1 &