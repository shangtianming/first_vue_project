pipeline{
	
	agent any
	stages{
		stage("fileExists") {
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
	}
	post {
		always {
			script {
				// deleteDir()
				println "结束"
			}
		}
	}
}