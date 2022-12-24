kubectl delete all --all -n loan-calculator
kubectl delete all --all -n kong
kubectl delete all --all -n argocd

kind delete cluster --name kazik
