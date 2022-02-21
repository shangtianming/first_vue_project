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
    }
}