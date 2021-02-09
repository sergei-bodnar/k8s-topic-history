### Jobs
Veiew all jobs:
`kubectl get jobs`

Vew a job details:
`kubectl describe job JOB_NAME`
or
`kubectl describe -f CREATE_SCRIPT`

### Simple Job - job-date

Create the job:
`kubectl apply -f ./job-date.yaml`

View the job logs:
`kubectl logs POD_NAME`

Delete the date job:
`kubectl delete POD_NAME`

### Simple CronJob - date-job
All commands the same as for the regular job.
To create a simple cron job use this script - cronjob-date.yaml
