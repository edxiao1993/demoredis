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
动态注入bean，以及动态删除bean的方法，这播客写得挺不错的，可以参考参考：
https://www.cnblogs.com/eternityz/p/12241143.html