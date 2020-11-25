
简单分库分表规则
https://www.dazhuanlan.com/2020/01/17/5e210a2b4706e/

自定义规则分库分表规则
https://www.freesion.com/article/3959674242/





四、系统是否分库分表分析
mysql单表经验

300W Mysql 可以轻松抗住
600 W 数据开始卡，优化可以解决（表结构，索引设计）
800W ~ 1000W 牛逼的DBA 优化都会遇到瓶颈
一般MySQL单表1000W左右的数据是可以不需要考虑分表的。当然，除了考虑当前的数据量和性能情况时，我们需要提前考虑系统半年到一年左右的业务增长情况。但是要避免过度设计（考虑了很多未来几年的需求。例如一张表在未来几年内 数据预计会达到几千万（存在很多的不确定性））

根据数据量增长速度，选择实现步骤

第一步：不分库不分表

第二步：同库内的分表

第三步：分库分表

不要过度设计，一上来玩大的就进行分库分表

分库如果多个实例存在同一台服务器上，只是解决了数据库最大连接数的问题

但是 io（数据库数据是存储在硬盘上，每次获取都需要去硬盘把数据捞出来）,cpu 等服务器资源瓶颈并没有解决。数据库的性能取决于服务器等性能



5, 绑定表——联表查询防止出现笛卡尔积现象
绑定表： 指分片规则一致的主表和子表。
例如：t_order表和t_order_item表，均按照order_id分片，则此两张表互为绑定表关系。绑定表之间的多表关联查询不会出现笛卡尔积关联，关联查询效率将大大提升。如果SQL为：

SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);
1
在不配置绑定表关系时，假设分片键order_id将数值10路由至第0片，将数值11路由至第1片，那么路由后的SQL应该为4条，它们呈现为笛卡尔积：

SELECT i.* FROM t_order_0 o JOIN t_order_item_0 i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);

SELECT i.* FROM t_order_0 o JOIN t_order_item_1 i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);

SELECT i.* FROM t_order_1 o JOIN t_order_item_0 i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);

SELECT i.* FROM t_order_1 o JOIN t_order_item_1 i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);
1
2
3
4
5
6
7
在配置绑定表关系后，路由的SQL应该为2条：

SELECT i.* FROM t_order_0 o JOIN t_order_item_0 i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);

SELECT i.* FROM t_order_1 o JOIN t_order_item_1 i ON o.order_id=i.order_id WHERE o.order_id in (10, 11);
1
2
3
其中t_order在FROM的最左侧，ShardingSphere将会以它作为整个绑定表的主表。 所有路由计算将会只使用主表的策略，那么t_order_item表的分片计算将会使用t_order的条件。故绑定表之间的分区键要完全相同。

如何配置绑定表
通过sharding.jdbc.config.sharding.binding-tables[0]=t_order,t_order_item配置
