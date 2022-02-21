pipeline{
	agent any
	stages{
		stage("dir") {
			steps{
			    println env.WORKSPACE
			    dir("${env.WORKSPACE}/testdata"){
				    sh "pwd"
			    }
			}
		}
	}
	post {
		always {
			script {
				//写相关清除/恢复环境等操作代码
				deleteDir()
			}
		}
	}
}