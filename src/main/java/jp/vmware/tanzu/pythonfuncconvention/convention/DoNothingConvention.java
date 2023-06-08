package jp.vmware.tanzu.pythonfuncconvention.convention;

import io.kubernetes.client.openapi.models.V1PodTemplateSpec;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContext;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContextSpec;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContextStatus;
import org.springframework.stereotype.Component;

@Component
public class DoNothingConvention implements Convention {

	@Override
	public PodConventionContextStatus handler(PodConventionContext podConventionContext) {

		String[] appliedConventions = { "do-nothing" };

		V1PodTemplateSpec podTemplateSpec = podConventionContext.spec().template();

		return new PodConventionContextStatus(podTemplateSpec, appliedConventions);
	}

}
