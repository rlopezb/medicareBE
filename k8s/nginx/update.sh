kubectl set image deployment/nginx nginx=rlopezb/medicare-fe:latest
kubectl wait --for=condition=available --timeout=300s deployment nginx
kubectl port-forward --address 0.0.0.0 service/nginx 8008:80 > /dev/null 2>&1 &
