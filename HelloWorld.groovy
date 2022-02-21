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
        stage('Utility Steps method---readProperties') {
            steps {
                script {
					properties_file = env.WORKSPACE + "/jenkins.properties"
                    props = readProperties interpolate: true, file: properties_file
                    println props
                }
            }
        }
    }
}