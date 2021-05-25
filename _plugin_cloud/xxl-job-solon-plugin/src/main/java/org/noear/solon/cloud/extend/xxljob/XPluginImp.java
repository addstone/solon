package org.noear.solon.cloud.extend.xxljob;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.noear.solon.SolonApp;
import org.noear.solon.cloud.CloudManager;
import org.noear.solon.cloud.annotation.CloudJob;
import org.noear.solon.cloud.extend.xxljob.impl.CloudJobExtractor;
import org.noear.solon.cloud.extend.xxljob.impl.XxlJobAutoConfig;
import org.noear.solon.cloud.extend.xxljob.impl.XxlJobMethodExtractor;
import org.noear.solon.cloud.extend.xxljob.service.CloudJobServiceImpl;
import org.noear.solon.core.Aop;
import org.noear.solon.core.Plugin;

/**
 * @author noear
 * @since 1.4
 */
public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        //add extractor for bean method
        Aop.context().beanExtractorAdd(XxlJob.class, new XxlJobMethodExtractor());
        Aop.context().beanExtractorAdd(CloudJob.class, new CloudJobExtractor());

        Aop.context().beanMake(XxlJobAutoConfig.class);

        Aop.beanOnloaded(() -> {
            try {
                //
                XxlJobExecutor executor = Aop.get(XxlJobExecutor.class);

                executor.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //登记Job服务
        CloudManager.register(CloudJobServiceImpl.instance);
    }
}