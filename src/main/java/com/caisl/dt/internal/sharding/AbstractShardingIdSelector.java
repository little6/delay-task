package com.caisl.dt.internal.sharding;

import com.caisl.dt.job.DelayTaskLoadJob;
import com.caisl.dt.system.helper.ShardingItemHelper;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobAPIFactory;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.google.common.base.Optional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * AbstractShardingIdSelector
 * 抽象分片id选择器
 *
 * @author caisl
 * @since 2019-05-09
 */
public abstract class AbstractShardingIdSelector implements ShardingIdSelector {

    @Resource
    private ShardingItemHelper shardingItemHelper;

    @Override
    public Integer select(boolean isLocalNode) {
        //获取分片id集合
        List<Integer> shardingIds = shardingItemHelper.getShardingIds(isLocalNode);
        if (CollectionUtils.isEmpty(shardingIds)) {
            return null;
        }
        if (shardingIds.size() == 0) {
            return shardingIds.get(0);
        }
        return doSelect(shardingIds);
    }

    /**
     * 子类自己实现分区ID选择算法
     *
     * @param shardingIds
     * @return
     */
    protected abstract Integer doSelect(List<Integer> shardingIds);

}
