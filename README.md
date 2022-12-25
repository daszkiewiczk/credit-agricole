# To setup the cluster and run the app:

1. Install docker, kubectl and kind according to providers' instructions:

https://docs.docker.com/engine/install/

https://kubernetes.io/docs/tasks/tools/#kubectl

https://kind.sigs.k8s.io/docs/user/quick-start/#installation



2. git clone the repository

`git clone https://github.com/daszkiewiczk/credit-agricole`



3. setup the cluster and build the images 

```
cd cicd

chmod u+x setup.sh build.sh

./setup.sh

./build.sh
```

Grafana is available at grafana.local

```
echo '127.0.0.1 grafana.local' > /etc/hosts
```

# Troubleshooting

Pod errors due to “too many open files”

This may be caused by running out of inotify resources. Resource limits are defined by fs.inotify.max_user_watches and fs.inotify.max_user_instances system variables. For example, in Ubuntu these default to 8192 and 128 respectively, which is not enough to create a cluster with many nodes.

To increase these limits temporarily run the following commands on the host:
sudo sysctl fs.inotify.max_user_watches=524288
sudo sysctl fs.inotify.max_user_instances=512

sudo sysctl fs.inotify.max_user_watches=524288
sudo sysctl fs.inotify.max_user_instances=512


kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
