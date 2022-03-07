pipeline{
	
	agent any
	stages{
		stage("send mail test") {
			steps{
				script {
					mail to: '570375381@qq.com',
                    subject: "Running Pipeline: ${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BUILD_URL}"
				}
			}
		}
	}
}