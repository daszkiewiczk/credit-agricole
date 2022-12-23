kind load docker-image daszkiewiczk/loan-calculator-backend:latest --name kazik
kind load docker-image daszkiewiczk/loan-calculator-frontend:latest --name kazik

kubectl apply -f ../k8s/*.yaml
