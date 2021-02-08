## Topics History application

### Preparations
* Install k8s sig - kind
  ```
  https://kind.sigs.k8s.io/docs/user/quick-start
  ```

* Build the docker image
  ```
  docker build -t sergei-bodnar/topics-history .
  ```

* Provide the application image to the kind registry
  ```
  kind load docker-image sergei-bodnar/topics-history
  ```

### 1. Deployment + PersistentVolume + PersistentVolumeClaim infrastructure
This infrastructure consists of:
* Deployment with 1 POD
* PersistentVolume

The POD uses a persistent volume to save its state.

1.1 Prepare a persistent volume
```
kubectl apply -f k8s/persistent-volume.yaml
```
check the volume was created:
```
kubectl get pv
kubectl get pvc
```

1.2 Create the application infrastructure
```
kubectl apply -f k8s/infrastructure.yaml
```
check the infrastructure was created:
```
kubectl get all
```
enter into a pod
```
kubectl exec -ti POD_NAME -- bash
```
create topic
```
curl -d "topic=Topic 1" localhost:8080/addTopic
curl -d "topic=Topic 2" localhost:8080/addTopic
curl -d "topic=Topic 3" localhost:8080/addTopic
```
check the created topics
```
curl localhost:8080/list
```
then delete the infrastructure, create again and check whether the created topics exist 
```
kubectl delete -f k8s/infrastructure.yaml
kubectl apply -f k8s/infrastructure.yaml
kubectl exec -ti POD_NAME -- bash
curl localhost:8080/list
```

### 2. StatefulSet infrastructure
This StatefulSet infrastructure solves the same goal as the previous Deployment infrastructure.
This approach is useful in case when required more than one POD.
All responsibility to create PODs and related PersistentVolumeClaim controlled by a StatefulSet.
Each replica has its own number. We can use this number to refer to a specific instance.
Also, the StatefulSet infrastructure implemented `Liveness`, `Readiness` and `Startup` probes. 

2.1 Create the StatefulSet infrastructure
```
kubectl apply -f k8s/infrastructure-statefullset.yaml
```
