#!/bin/bash

#create the cluster
kind create cluster --name kazik --config ../cluster.yaml

#create argocd
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
kubectl -n argocd patch service argocd-server -p '{"spec":{"type":"NodePort"}}' #makes the dashboard accessible
#define argocd objects
kubectl apply -f argocd-repository.yaml #repository secret 
kubectl apply -f argocd-application.yaml -n argocd 
kubectl apply -f argocd-monitoring-application.yaml -n argocd
kubectl apply -f argocd-keel.yaml -n argocd
kubectl apply -f argocd-kic.yaml -n argocd