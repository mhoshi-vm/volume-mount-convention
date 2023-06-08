package jp.vmware.tanzu.pythonfuncconvention.convention;

import io.kubernetes.client.openapi.models.*;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContext;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContextStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class VolumeMountConvention implements Convention {

	@Override
	public PodConventionContextStatus handler(PodConventionContext podConventionContext) {
		String[] appliedConventions = { "volumeMountConvention" };

		V1PodTemplateSpec podTemplateSpec = podConventionContext.spec().template();

		V1ObjectMeta meta = podConventionContext.metadata();
		Map<String, String> labels = meta.getLabels();

		if (labels != null &&
				labels.get("jp.vmware.tanzu/volumemount") != null &&
				labels.get("jp.vmware.tanzu/volumemount").equals("nfs") &&
				labels.get("jp.vmware.tanzu/volumemount.server") != null &&
				labels.get("jp.vmware.tanzu/volumemount.path") !=null &&
				labels.get("jp.vmware.tanzu/volumemount.mountpath") !=null)	{

			V1Volume volume = new V1Volume();
			V1NFSVolumeSource nfsVolumeSource = new V1NFSVolumeSource();
			nfsVolumeSource.setServer(labels.get("jp.vmware.tanzu/volumemount.server"));
			nfsVolumeSource.setPath(labels.get("jp.vmware.tanzu/volumemount.path"));

			volume.setName("nfs-mount");
			volume.setNfs(nfsVolumeSource);

			V1PodSpec podSpec = podTemplateSpec.getSpec();
			if (podSpec != null) {
				List<V1Volume> volumeList = podSpec.getVolumes() != null ?
						podSpec.getVolumes() :
						new ArrayList<>();
				volumeList.add(volume);
				podSpec.setVolumes(volumeList);

				for (V1Container container : podSpec.getContainers()) {
					List<V1VolumeMount> volumeMounts =
							container.getVolumeMounts() != null ?
									container.getVolumeMounts() :
									new ArrayList<>();
					V1VolumeMount volumeMount = new V1VolumeMount();
					volumeMount.setName("nfs-mount");
					volumeMount.setMountPath(labels.get("jp.vmware.tanzu/volumemount.mountpath"));
					volumeMounts.add(volumeMount);
					container.setVolumeMounts(volumeMounts);
				}
			}

		}

		return new PodConventionContextStatus(podTemplateSpec, appliedConventions);
	}

}
