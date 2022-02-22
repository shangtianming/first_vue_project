pipeline{
    agent any
    stages{
        stage("dir") {
            steps{
                // 如果没有这个目录，会自动创建这个目录
                dir("${env.WORKSPACE}/testdata"){
                    sh "pwd"
                }
            }
        }
		stage("fileExists") {
			steps{
				script {
					json_file = "${env.WORKSPACE}/test_jenkins.log"
					if(fileExists(json_file) == true) {
						echo("log file is exists")
					}else {
						error("here haven't find log file")
					}
				}
			}
		}
		stage("isUnix") {
			steps{
				script {
					if(isUnix() == true) {
						echo("this jenkins job running on a linux-like system")
					}else {
						error("the jenkins job running on a windows system")
					}
				}
			}
		}
		stage("pwd()") {
			steps{
				script {
					sh("pwd")
					println "==========="
					println pwd()
				}
			}
		}
    }
    post {
        always {
            script {
                //写相关清除/恢复环境等操作代码
                //deleteDir()
                println "结束"
            }
        }
    }
}