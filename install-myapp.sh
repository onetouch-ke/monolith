helm install alb-controller alb-controller
helm install monolith mychart
kubectl create namespace argocd
helm repo add argo https://argoproj.github.io/argo-helm
helm repo update
helm install argocd argo/argo-cd -n argocd --set server.extraArgs="{--insecure}" --set server.insecure=true     # Only HTTP Health Check
helm install ingress ingress
