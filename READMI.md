### Topics History application
#### 1. Install k8s sig - kind
```
https://kind.sigs.k8s.io/docs/user/quick-start
```

#### 2. Build the docker image
```
docker build -t sergei-bodnar/topics-history .
```

#### 3. prepare a persistent volume folder 
```
kubectl apply -f k8s/persistent-volume.yaml
```
check the volume was created:
```
kubectl get pv
kubectl get pvc
```

#### 4. provide the application image to the kind registry
```
kind load docker-image sergei-bodnar/topics-history
```

#### 5. Create an application infrastructure
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
