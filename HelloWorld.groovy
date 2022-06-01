pipeline{
	agent any
	stages{
		stage("init") {
			steps{
				script {
					// 设置超时5s，默认单位是分钟
					timeout(time: 50, unit: 'SECONDS') {
						//initialRecurrencePeriod ：设置重试之间的初始等待时间（以毫秒为单位）。
						// 默认为 250 毫秒。每次失败都会将尝试之间的延迟减慢至最多 15 秒
						//quiet ：如果为真，则每次检查条件时该步骤都不会记录消息。默认为假。类型： boolean
						println "是unix系统则返回True" + isUnix()
						waitUntil(initialRecurrencePeriod: 1000,quiet: true){
							script {
								isUnix()
							}
						}
					}
				}
			}
		}
		stage("env") {
			steps{
				script {
					withEnv(['java_home=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-3.b13.el6_10.i386/jre']) {
						sh("$java_home/bin/java -version")
						println "test with withEnv feature"
					}
				}
			}
		}
	}
}
