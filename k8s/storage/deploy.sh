minikube mount /home/rlopezb/minikube/pv-mysql-claim0:/data/pv-mysql-claim0 --uid 999 --gid 999 > /dev/null 2>&1 &
minikube mount /home/rlopezb/minikube/pv-jenkins-claim0:/data/pv-jenkins-claim0 > /dev/null 2>&1 &

kubectl apply -f minikube-storageClass.yaml