

```java
2022-05-08 15:01:10.437  INFO 25444 --- [nio-8082-exec-4] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [169.254.134.162:8091:7422192504605822988]
 2022-05-08 15:01:10.438  WARN 25444 --- [nio-8082-exec-4] c.a.druid.pool.DruidAbstractDataSource   : discard long time none received connection. , jdbcUrl : jdbc:mysql://127.0.0.1:3306/shirodb?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=UTF-8, version : 1.2.3, lastPacketReceivedIdleMillis : 89284
 2022-05-08 15:01:10.441  INFO 25444 --- [nio-8082-exec-4] c.c.e.d.transaction.UserInfoController   : UserAddDto(p=林, u=林)
 2022-05-08 15:01:10.469  INFO 25444 --- [nio-8082-exec-4] c.c.e.d.transaction.UserInfoController   : UserInfo(id=4, name=林, petId=12)save:true
 2022-05-08 15:01:10.478  INFO 25444 --- [nio-8082-exec-4] i.seata.tm.api.DefaultGlobalTransaction  : Suspending current transaction, xid = 169.254.134.162:8091:7422192504605822988
 2022-05-08 15:01:10.478  INFO 25444 --- [nio-8082-exec-4] i.seata.tm.api.DefaultGlobalTransaction  : [169.254.134.162:8091:7422192504605822988] commit status: Committed
 2022-05-08 15:01:10.989  INFO 25444 --- [h_RMROLE_1_1_32] i.s.c.r.p.c.RmBranchCommitProcessor      : rm client handle branch commit process:xid=169.254.134.162:8091:7422192504605822988,branchId=7422192504605822990,branchType=AT,resourceId=jdbc:mysql://127.0.0.1:3306/shirodb,applicationData=null
 2022-05-08 15:01:10.990  INFO 25444 --- [h_RMROLE_1_1_32] io.seata.rm.AbstractRMHandler            : Branch committing: 169.254.134.162:8091:7422192504605822988 7422192504605822990 jdbc:mysql://127.0.0.1:3306/shirodb null
 2022-05-08 15:01:10.990  INFO 25444 --- [h_RMROLE_1_1_32] io.seata.rm.AbstractRMHandler            : Branch commit result: PhaseTwo_Committed
```



```mysql
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
```

