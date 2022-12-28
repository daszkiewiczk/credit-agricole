#!/bin/bash

#create the cluster
kind create cluster --name kazik --config ../cluster.yaml --image kindest/node:v1.25.3

#create argocd
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
kubectl -n argocd patch service argocd-server -p '{"spec":{"type":"NodePort"}}' #makes the dashboard accessible

kubectl apply -f https://raw.githubusercontent.com/Kong/kubernetes-ingress-controller/master/deploy/single/all-in-one-dbless.yaml
kubectl patch deployment -n kong ingress-kong -p '{"spec":{"template":{"spec":{"containers":[{"name":"proxy","ports":[{"containerPort":8000,"hostPort":80,"name":"proxy","protocol":"TCP"},{"containerPort":8443,"hostPort":443,"name":"proxy-ssl","protocol":"TCP"}]}],"nodeSelector":{"ingress-ready":"true"},"tolerations":[{"key":"node-role.kubernetes.io/control-plane","operator":"Equal","effect":"NoSchedule"},{"key":"node-role.kubernetes.io/master","operator":"Equal","effect":"NoSchedule"}]}}}}'

#define argocd objects
kubectl apply -f argocd-repository.yaml #repository secret 
kubectl apply -f argocd-application.yaml -n argocd 
kubectl apply -f argocd-monitoring-application.yaml -n argocd
kubectl apply -f argocd-keel.yaml -n argocd
