println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{

	agent any
	stages{
		stage("writeFile demo") {
			steps{
				script {
					write_file_path = "${env.WORKSPACE}/test_jenkins.txt"
					file_contents = "Hello Anthony!! 这是一个测试例子"
					// 写文件
					writeFile file: write_file_path, text: file_contents, encoding: "UTF-8"
					// 读文件
					fileContents = readFile file: write_file_path, encoding: "UTF-8"
					println fileContents
				}
			}
		}
	}
}
