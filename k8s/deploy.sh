minikube start
for d in storage mysql tomcat nginx ingress ; do (cd "$d" && ./deploy.sh); done
