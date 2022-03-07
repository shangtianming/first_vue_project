pipeline{
	
	agent any
	stages{
		stage("send mail test") {
			steps{
				script {
					mail to: 'yin921125@qq.com',
					cc: 'xxxxx@qq.com',
					charset:'UTF-8',			// or GBK/GB18030
					mimeType:'text/plain',		// or text/html
					subject: "Running Pipeline: ${currentBuild.fullDisplayName}",
					body: "Something is wrong with ${env.BUILD_URL}, just for test send mail via pipeline code"
				}
			}
		}
	}
}