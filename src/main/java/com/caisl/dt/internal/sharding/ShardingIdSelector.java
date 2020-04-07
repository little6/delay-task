package com.caisl.dt.internal.sharding;

/**
 * ShardingIdSelector
 *
 * 分片id选择器接口
 *
 * @author caisl
 * @since 2019-05-08
 */
public interface ShardingIdSelector {
    /**
     * 选取一个分片ID
     *
     * @return
     */
    Integer select(boolean isLocalNode);
}
