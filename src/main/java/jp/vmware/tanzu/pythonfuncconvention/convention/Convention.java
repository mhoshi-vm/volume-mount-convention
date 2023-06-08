package jp.vmware.tanzu.pythonfuncconvention.convention;

import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContext;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContextSpec;
import jp.vmware.tanzu.pythonfuncconvention.model.PodConventionContextStatus;

public interface Convention {

	PodConventionContextStatus handler(PodConventionContext podConventionContext);

}
