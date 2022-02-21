pipeline{
	agent any
	stages{
		stage("dir") {
			steps{
			    println env.WORKSPACE
				// 如果没有这个目录，会自动创建这个目录
			    dir("${env.WORKSPACE}/testdata"){
				    sh "pwd"
			    }
				
				echo ("list all files under current workd directory")
				sh("ls -al ${env.WORKSPACE}")
				echo("some info logs output")
				error("some error method output")
			}
		}
		stage("test") {
			steps{
				echo("some info logs output=======test")
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