kubectl apply -f mysql-claim0-persistentVolume.yaml
kubectl apply -f mysql-claim0-persistentVolumeClaim.yaml
kubectl apply -f mysql-deployment.yaml
kubectl apply -f mysql-service.yaml