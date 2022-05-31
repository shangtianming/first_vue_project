pipeline{
	agent any
	stages{
		stage("init") {
			steps{
				script {
					json_file = "${env.WORKSPACE}/package.json"
					file_contents = readFile json_file
					println file_contents
				}
			}
		}
		stage("retry and sleep") {
			steps{
				script{
					try {
						retry(3) {
							println "here we are test retry fuction"
							sleep 5
							println 10/0
						}
					}catch (Exception e) {
						println e
					}
				}
			}
		}
	}
}
