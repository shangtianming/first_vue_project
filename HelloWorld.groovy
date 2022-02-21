// import hudson.model.*;

pipeline {
    agent any	
    stages {
		stage('env') {
		    steps {
		        script {
					println env.JOB_NAME
					println env.BUILD_NUMBER
					println env.WORKSPACE
		        }
		    }
		}
        stage('Utility Steps method---findFiles') {
            steps {
                script {
                    files = findFiles(glob: '**/*.log')
                    println files[0].name
                }
            }
        }
		stage('Utility Steps method---readJSON') {
		    steps {
		        script {
					file_path = env.WORKSPACE + "/package.json"
					prop = readJSON file : file_path
		            println prop
					println prop.name
		        }
		    }
		}
		stage('Utility Steps method---writeJSON') {
		    steps {
		        script {
					file_path = env.WORKSPACE + "/testdata/new_json.json"
					input_json =  env.WORKSPACE + "/package.json"
					writeJSON file: file_path, json: input_json
		        }
		    }
		}
    }
}