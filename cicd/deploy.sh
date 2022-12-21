kind load docker-image daszkiewiczk/loan-calculator-backend:latest --name kazik
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/backend-deployment.yaml 
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/backend-service.yaml 
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/ingress.yaml

kubectl apply -f ../loan-calculator-app/loan-calculator-backend/postgres-pvc.yaml
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/database-deployment.yaml



