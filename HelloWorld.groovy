module_test = load env.WORKSPACE + "/test_groovy.groovy"
pipeline{
	agent any
	stages{
		stage("发邮件demo 1") {
			steps{
				script {
					mail to: '570375381@qq.com',
					subject: "Running Pipeline: ${currentBuild.fullDisplayName}",
					body: "Something is wrong with ${env.BUILD_URL}"
				}
			}
		}
		stage("发邮件demo 2") {
			steps{
				script {
					mail to: 'yin921125@qq.com',
					cc: 'xxxxx@qq.com',			// 抄送
					charset:'UTF-8',			// or GBK/GB18030
					mimeType:'text/plain',		// or text/html
					subject: "Running Pipeline: ${currentBuild.fullDisplayName}",
					body: "Something is wrong with ${env.BUILD_URL}, just for test send mail via pipeline code"
				}
			}
		}
		stage("发邮件demo 3") {
			steps{
				script {
					subject = "Jenkins Job : " + env.JOB_NAME + "/" + env.BUILD_ID
					result_url = env.BUILD_URL + "console"
					to_email_address_list = "yin921125@qq.com,yin921125@163.com"
					
					text = """
					<!DOCTYPE html>
					<html>
						<head>
							<meta charset="utf-8">
							<title></title>
						</head>
						<body>
							<div>
								<h1>Summary</h1>
								<div>
									<h2>Jenkins Build</h2>
									<ul>
										<li>Job URL : <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></li>
										<li>Build Result URL : <a href='${result_url}'>${result_url}</a></li>
									</ul>
								</div>
								<div>
									<h2>GIT Branch</h2>
									<ul>
										<li>这是一个测试分支</li>
									</ul>
								</div>
							</div>
						</body>
					</html>
					"""
					mail body: text, subject: subject,  mimeType: 'text/html', to: to_email_address_list
				}
			}
		}
		post{
			failure {
				script {
					module_test.send_email_results("Failed","Master","yin921125@qq.com,yin921125@163.com")
				}
			}
			success {
				script {
					module_test.send_email_results("Success","Master","yin921125@163.com")
				}
			}
		}
	}
}