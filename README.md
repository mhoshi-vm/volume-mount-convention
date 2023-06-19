```
tanzu apps workload apply hello-nodejs \
  --app hello-nodejs \
  --git-repo https://github.com/making/hello-nodejs \
  --git-branch master \
  --label "jp.vmware.tanzu/volumemount=nfs" \
  --label "jp.vmware.tanzu/volumemount-server=192.168.102.243" \
  --label "jp.vmware.tanzu/volumemount-path=path.nfs" \
  --label "jp.vmware.tanzu/volumemount-mountpath=path.mountpath" \
  -n convention-demo \
  --type web \
  -y
```