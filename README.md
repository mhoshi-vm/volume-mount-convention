```
tanzu apps workload apply --label apps.tanzu.vmware.com/has-tests=true --git-repo https://github.com/mhoshi-vm/volume-mount-convention --git-branch main --type worker volumemountconvention -n convention-demo
```