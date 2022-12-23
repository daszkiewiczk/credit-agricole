kubectl delete deployment loan-calculator-backend
kubectl delete svc loan-calculator-backend-service

kubectl delete deployment postgres-deployment
kubectl delete svc postgres-service

kubectl delete deployment frontend-deployment
kubectl delete deployment frontend-service

kind delete cluster --name kazik
