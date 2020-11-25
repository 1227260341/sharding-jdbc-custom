//分库的自定义类
package com.zj.content.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

//实现自定义接口
public class PreciseModuloDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    /**
     * @param collection 存放的是所有的库的列表，这里代表master04091,master04092 master04091(2020~2024),master04092(2025~2029)
     * @return 将数据写入的哪个库
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> pre) {
        try {
            // 配置的分库分片的sharding-column对应的值,也就是具体时间
            String str = pre.getValue();
            if (str.isEmpty()) {
                throw new UnsupportedOperationException("pre is null");
            }
            // each为每个库的名字
            for (String each : collection) {
                // 得到具体年,截取字符串要头不要尾
                String value = StringUtils.substring(str, 0, 4);
                // 以5年为一个库,例如：2020~2024
                int c = Integer.parseInt(value) - 2020;
                // 算差值，拿当前时间减去2020，用差除于5，会得到小数，用int强转，只要整数，这样为0的就放到1库，为1的就放到2库
                int database_hou_zhui = c / 5;// 0.2（2021年放到1库），1.2（2026年放到2库）
                if (each.endsWith(Integer.toString(database_hou_zhui + 1))) {
                    // 扔到后缀是database_hou_zhui+1的库,判断当前这个库是否符合我条件，
                    // 而不是我去找符合我条件的库，因为没法找，好多库呢
                    return each;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
