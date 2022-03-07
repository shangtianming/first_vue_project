def send_email_results(status,GITBranch,to_email_address_list) {
	def subject = "Jenkins Job : " + env.JOB_NAME + "/" + env.BUILD_ID + " has " +  status
	def result_url = env.BUILD_URL + "console"
    
    def text = """
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
					<li>这是测试引用其他groovy文件的邮件${GITBranch}</li>
				</ul>
			</div>
		</div>
	</body>
	</html>
	"""
	
    mail body: text, subject: subject,  mimeType: 'text/html', to: to_email_address_list
}

return this