minikube start
for d in security storage mysql tomcat nginx ingress ; do (cd "$d" && ./deploy.sh); done
