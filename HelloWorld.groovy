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
                    file_path_read = env.WORKSPACE + "/package.json"
                    rd_json = readJSON file : file_path_read
                    println rd_json
                    println rd_json.name
                }
            }
        }
        stage('Utility Steps method---writeJSON') {
            steps {
                script {
                    file_path_write = env.WORKSPACE + "/test_jenkins.json"
                    input_json =  env.WORKSPACE + "/package.json"
                    input = readJSON file : input_json
                    
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