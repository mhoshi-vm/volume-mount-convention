```
tanzu apps workload apply --build-env "BP_JVM_VERSION=17"  --annotation autoscaling.knative.dev/minScale=1  --label apps.tanzu.vmware.com/has-tests=true --git-repo https://github.com/mhoshi-vm/volume-mount-convention --git-branch main --type web volumemountconvention -n convention-demo
```