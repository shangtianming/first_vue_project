pipeline {
    agent any    
    stages {
        stage('Utility Steps method---writeJSON') {
            steps {
                script {
                    file_path_write = env.WORKSPACE + "/test_jenkins.json"
                    input_json =  env.WORKSPACE + "/package.json"
                    input = c file : input_json
                    
                    writeJSON file: file_path_write, json: input
                }
            }
        }
		stage('Utility Steps method---readProperties') {
		    steps {
		        script {
					properties_file = env.WORKSPACE + "/test_jenkins.properties"
		            props = readProperties interpolate: true, file: properties_file
		            println props
		        }
		    }
		}
		stage('Utility Steps method---readYaml') {
		    steps {
		        script {
					yaml_file = env.WORKSPACE + "/test_jenkins.yaml"
		            y = readYaml file : yaml_file
		            println y
		        }
		    }
		}
    }
}