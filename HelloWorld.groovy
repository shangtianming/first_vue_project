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
                    echo files[0].name
                }
            }
        }
		stage('Utility Steps method---readJSON') {
		    steps {
		        script {
					json_file = env.WORKSPACE + "/package.json"
					prop = readJSON file : json_file
		            def jsons = readJSON(prop)
		            echo jsons
		        }
		    }
		}
    }
}