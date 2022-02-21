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
                    file_path = env.WORKSPACE + "/new_json.json"
                    input_json =  env.WORKSPACE + "/package.json"
                    input = c file : input_json
                    
                    writeJSON file: file_path, json: input
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