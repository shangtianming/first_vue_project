pipeline{
	
	agent any
	stages{
		stage("fileExists") {
			steps{
				script {
					json_file = "${env.WORKSPACE}/testdata/test_json.json"
					if(fileExists(json_file) == true) {
						echo("json file is exists")
					}else {
						error("here haven't find json file")
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