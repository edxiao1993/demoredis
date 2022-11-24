# demoredis
demo project for redis

#### Nov22
还是有点没搞明白怎么把本地的代码提交到 github……
damn it

1 首先，初始化本地仓库
2 创建远程仓库
3 关联本地仓库与远程仓库
 git add remote origin [git@address.git]
4 git pull origin master --allow-unrelated-histories

5 {从这里开始就很奇怪了，但根据提示也能顺利搞定……}
5.1 可能需要 git config pull.rebase false -- false:merge true:rebase
5.2 可能需要 git fetch，然后切换到 master 分支。本地创建的分支好像是 main……

#### Nov24
动态注入bean，以及动态删除bean的方法，这博客写得挺不错的，可以参考参考：
https://www.cnblogs.com/eternityz/p/12241143.html

#### Nov25
redisson 的配置说明：
https://github.com/redisson/redisson/wiki/2.-Configuration

redisson common configuration:::
connectionListener: connection listener which is triggered when Redisson connected/disconnected to Redis server.
lockWatchdogTimeout: RLock object watchdog timeout in milliseconds.
checkLockSyncedSlaves: Defines whether to check synchronized slaves amount with actual slaves amount after lock acquisition.

redisson singleserver configuration:::
address: redis://host:port
connectionMinimumIdleSize: Minimum idle Redis connection amount. // 24: 会不会太大了？
connectionPoolSize: Redis connection maximum pool size. // 64
idleConnectionTimeout: ... it will closed and removed from pool // 10000
connectTimeout: Timeout during connecting to any Redis server. // 10000
timeout: Redis server response timeout. // 30000
retryAttempts: Error will be thrown if Redis command can't be sended to Redis server after retryAttempts. 
 But if it sent succesfully then timeout will be started. // 3
retryInterval: Time interval after which another one attempt to send Redis command will be executed. // 1500
pingConnectionInterval: PING command sending interval per connection to Redis. Defined in milliseconds. Set 0 to disable. // 30000, NOTE
keepAlive: Enables TCP keepAlive for connection.
tcpNoDelay: Enables TCP noDelay for connection.

redisson cluster configuration:::
checkSlotsCoverage: Enables cluster slots check during Redisson startup.
nodeAddresses: // add redis://host:port or add all in List<String>
scanInterval: Redis cluster scan interval in milliseconds. // 1000
readMode: Set node type used for read operation. Available values: SLAVE, MASTER, MASTER_SLAVE // NOTE
loadBalancer:
subscriptionConnectionMinimumIdleSize: Minimum idle connection pool size for subscription (pub/sub) channels. // 1, RLock 有使用到
subscriptionConnectionPoolSize: // 50
slaveConnectionMinimumIdleSize: Redis 'slave' node minimum idle connection amount for each slave node. // 24
slaveConnectionPoolSize: Redis 'slave' node maximum connection pool size for each slave node // 64
masterConnectionMinimumIdleSize: Redis 'master' node minimum idle connection amount for each master node. 
 Master node is also added as slave node and keeps idle connections as slave. 
 These connections remain in slave connection pool but in non-active state.
 They are preserved for case when your cluster lost all slaves. 
 In this case master is started use as slave and idle-connections become active. // 24
masterConnectionPoolSize: Redis 'master' node maximum connection pool size // 24
connectTimeout: same as single
timeout: same as single
retryAttempts:
retryInterval:
keepAlive:
tcpNoDelay:


#### Lettuce configuration
Client Options:::
pingBeforeActivateConnection: PING before activating connection // true
autoReconnect: Auto-Reconnect // true
requestQueueSize: Request queue size // Integer.MAX_VALUE
disconnectedBehavior: A connection can behave in a disconnected state in various ways. 
 The auto-connect feature allows in particular to retrigger commands that have been queued while a connection is disconnected. 
 The disconnected behavior setting allows fine-grained control over the behavior:
  DEFAULT: Accept commands when auto-reconnect is enabled, reject commands when auto-reconnect is disabled
  ACCEPT_COMMANDS
  REJECT_COMMANDS
socketOptions: Options to configure low-level socket options for the connections kept to Redis servers. // 10 seconds

cluster::: 完全看不懂
enablePeriodicRefresh: ? // false




-- proxy mode 只在 Redisson Pro 才生效