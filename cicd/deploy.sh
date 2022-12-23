kind load docker-image daszkiewiczk/loan-calculator-backend:latest --name kazik
kind load docker-image daszkiewiczk/loan-calculator-frontend:latest --name kazik

kubectl apply -f ../loan-calculator-app/loan-calculator-backend/backend-deployment.yaml 
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/backend-service.yaml

kubectl apply -f ../loan-calculator-app/loan-calculator-backend/ingress.yaml

kubectl apply -f ../loan-calculator-app/loan-calculator-backend/postgres-pvc.yaml
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/database-deployment.yaml
kubectl apply -f ../loan-calculator-app/loan-calculator-backend/database-service.yaml

kubectl apply -f ../loan-calculator-app/loan-calculator-frontend/frontend-deployment.yaml
kubectl apply -f ../loan-calculator-app/loan-calculator-frontend/frontend-service.yaml



