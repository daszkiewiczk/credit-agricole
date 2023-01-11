>This repository was a winning entry in *DevOps Ninja* contest organized by Credit Agricole in collaboration with [Dell Technologies](https://github.com/dell) and [VMWare](https://github.com/vmware).

![DevOps Ninja](https://devopsninjakonkurs.pl/wp-content/themes/ca-dell/img/logo_devops.png)






>Kubernetes cluster and residing applicaiton were tested on Ubuntu 22.04

# To setup the cluster and run the app:

1. Install docker, kubectl and kind according to providers' instructions:

https://docs.docker.com/engine/install/

https://kubernetes.io/docs/tasks/tools/#kubectl

https://kind.sigs.k8s.io/docs/user/quick-start/#installation



2. git clone the repository

`git clone https://github.com/daszkiewiczk/credit-agricole`



3. setup the cluster

```
cd setup

chmod u+x setup.sh

./setup.sh
```



4. Loan calculator frontend application is available at `localhost`. You might have to allow pop-ups for the pdf file to open in new tab.

# Lifecycle

Application builds are automated with GitHub Actions (triggered with pushes to 'master' branch). Built images are then pushed to a DockerHub repository. K8s Deployments are configured to actively poll for changes in that image repository with [keel.sh](https://keel.sh/). 



Cluster's components are managed declaratively using [argocd](https://github.com/argoproj/argo-cd) objects with 'selfHeal' property turned on to prevent manual changes to cluster's objects.


Grafana is available at `grafana.local` and Prometheus is available at `prometheus.local`.

Login: admin

Password: prom-operator
```
echo '127.0.0.1 grafana.local' > /etc/hosts
echo '127.0.0.1 prometheus.local' > /etc/hosts
```


Repository is scanned for security vulnerabilities with [snyk](https://snyk.io/). Automatic pull requests are enabled and snyk-bot opens fix PRs on daszkiewiczk's behalf.





# Troubleshooting

>Pod errors due to “too many open files”
>
>This may be caused by running out of inotify resources. Resource limits are defined by fs.inotify.max_user_watches and fs.inotify.max_user_instances system >variables. For example, in Ubuntu these default to 8192 and 128 respectively, which is not enough to create a cluster with many nodes.
>
>To increase these limits temporarily run the following commands on the host:
>sudo sysctl fs.inotify.max_user_watches=524288
>sudo sysctl fs.inotify.max_user_instances=512
>
>sudo sysctl fs.inotify.max_user_watches=524288
>sudo sysctl fs.inotify.max_user_instances=512


To get the argocd admin password:
`kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d`

>IF for some reason automated build fails, there is a build.sh script provided in 'setup' directory `cd setup && ./build.sh`
